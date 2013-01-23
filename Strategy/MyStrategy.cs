using System;
using System.Collections;
using System.Collections.Generic;
using System.IO;
using Com.CodeGame.CodeTanks2012.DevKit.CSharpCgdk.Model;
using System.Linq;
using System.Diagnostics;

namespace Com.CodeGame.CodeTanks2012.DevKit.CSharpCgdk
{
    public sealed class MyStrategy : IStrategy
    {
        public static Dictionary<TankType, TankProperties> TankProperties = new Dictionary<TankType, TankProperties>()
        {
            { TankType.Medium, new MediumTank() },
            { TankType.Heavy, new HeavyTank() },
            { TankType.TankDestroyer, new DestroyerTank() }
        };

        public static Dictionary<ShellType, ShellProperties> ShellProperties = new Dictionary<ShellType, ShellProperties>()
        {
            { ShellType.Regular, new RegularShell() },
            { ShellType.Premium, new PremiumShell() }
        };

        public static TankType TankType { get { return TankType.Medium; } }
        public static TankProperties Properties { get { return TankProperties[TankType]; } }

        public static Tank Self { get; private set; }
        public static Point SelfLocation { get; private set; }
        public static World World { get; private set; }
        public static Dictionary<long, Tank> Tanks { get; private set; }
        public static Dictionary<long, TankPlus> TankPlus { get; private set; }
        //public static Dictionary<long, Bonus> Bonuses { get; private set; }
        //public static Dictionary<long, BonusPlus> BonusPlus { get; private set; }

        //public static Stopwatch Stopwatch = new Stopwatch();
        //public static double Time = 0;
        //private double _lol = 10000000;

        public static List<KeyValuePair<double, double>> AnglesRegular = new List<KeyValuePair<double, double>>();
        public static List<KeyValuePair<double, double>> AnglesPremium = new List<KeyValuePair<double, double>>();

        private static Bonus _currentBonus;

        public MyStrategy()
        {
            //Stopwatch.Start();
        }

        //public static void Set()
        //{
        //    Time = Stopwatch.ElapsedTicks;
        //}

        //public static void Lap(string comment)
        //{
        //    Console.WriteLine(comment + ": " + (Stopwatch.ElapsedTicks - Time));
        //    Set();
        //}

        public void Move(Tank self, World world, Move move)
        {
            Self = self;
            SelfLocation = new Point(Self.X, Self.Y);
            World = world;

            AnglesRegular.Clear();
            AnglesPremium.Clear();

            //for (int i = 0; i < _lol; ++i)
            //{
            //    i += 1;
            //    i -= 1;
            //}

            //Point tmpIntersection;
            //var t00 = Mathematics.DoSegmentsIntersect(new Point(10, 10), new Point(110, 15), new Point(60, 50), new Point(60, 0), out tmpIntersection);

            //return;

            //var t00 = Mathematics.Newton(x => x * x, x => 2 * x, 2, 0.00000001); // 1.41421356237 
            //var t01 = Mathematics.Newton(x => x * x * x, x => 3 * x * x, 2, 0.0000001); // 1.25992104989

            //var tmpShellType = ShellType.Regular;
            //double tmpDistance = 0;

            //foreach (Tank tmpTank in world.Tanks)
            //    if (tmpTank.PlayerName == "QuickStartGuy")
            //    {
            //        tmpDistance = Mathematics.Distance(new Point(Self.X, Self.Y), new Point(tmpTank.X, tmpTank.Y)) - 100;
            //        break;
            //    }

            //var t02 = Mathematics.Newton(
            //    t => (MyStrategy.ShellProperties[tmpShellType].InitialSpeed * (Math.Exp(MyStrategy.ShellProperties[tmpShellType].SpeedPowerRatio * t) - 1)) / MyStrategy.ShellProperties[tmpShellType].SpeedPowerRatio, // integral of speed
            //    t => MyStrategy.ShellProperties[tmpShellType].InitialSpeed * Math.Exp(MyStrategy.ShellProperties[tmpShellType].SpeedPowerRatio * t), // speed
            //    tmpDistance, Settings.TickApproximationEpsilon);
            
            //return;

            //Set();

            if (Tanks == null)
            {
                Tanks = new Dictionary<long, Tank>();

                foreach (Tank tmpTank in world.Tanks)
                    Tanks.Add(tmpTank.Id, tmpTank);

                TankPlus = new Dictionary<long, TankPlus>();

                foreach (Tank tmpTank in world.Tanks)
                    TankPlus.Add(tmpTank.Id, new TankPlus(tmpTank.Id));

                //Bonuses = new Dictionary<long, Bonus>();
                //BonusPlus = new Dictionary<long, BonusPlus>();
            }
            else
            {
                foreach (Tank tmpTank in world.Tanks)
                    Tanks[tmpTank.Id] = tmpTank;

                //List<long> tmpOutdatedBonuses = new List<long>();

                //foreach (BonusPlus tmpBonusPlus in BonusPlus.Values)
                //    if (tmpBonusPlus.DeathTick == World.Tick)
                //        tmpOutdatedBonuses.Add(tmpBonusPlus.Id);

                //foreach (long tmpBonusId in tmpOutdatedBonuses)
                //{
                //    Bonuses.Remove(tmpBonusId);
                //    BonusPlus.Remove(tmpBonusId);
                //}

                //foreach (Bonus tmpBonus in world.Bonuses)
                //    if (!Bonuses.ContainsKey(tmpBonus.Id))
                //    {
                //        Bonuses.Add(tmpBonus.Id, tmpBonus);
                //        BonusPlus.Add(tmpBonus.Id, new BonusPlus(tmpBonus.Id, World.Tick + Settings.BonusLifeTime));
                //    }
            }

            foreach (TankPlus tmpTankPlus in TankPlus.Values)
                tmpTankPlus.Update();

            if (!TankPlus[Self.Id].IsAlive)
                return;

            // SHOOTING -------------------------------------------------------------------------------------------------------------

            AnglesRegular.Sort((Comparison<KeyValuePair<double, double>>)((x, y) => y.Value.CompareTo(x.Value)));
            AnglesPremium.Sort((Comparison<KeyValuePair<double, double>>)((x, y) => y.Value.CompareTo(x.Value)));

            double tmpAngle = Self.Angle + Self.TurretRelativeAngle, bestAngle = tmpAngle;

            if (AnglesRegular.Count > 0 && AnglesRegular[0].Value > 0)
            {
                bestAngle = AnglesRegular[0].Key;

                if (Math.Abs(Mathematics.AngleDifference(tmpAngle, AnglesRegular[0].Key)) < 0.017453292519943) // 1 degree
                    move.FireType = FireType.Regular;
            }

            if (Self.PremiumShellCount > 0 && AnglesPremium.Count > 0 && (AnglesRegular.Count == 0 || AnglesPremium[0].Value > AnglesRegular[0].Value))
            {
                bestAngle = AnglesPremium[0].Key;

                if (Math.Abs(Mathematics.AngleDifference(tmpAngle, AnglesPremium[0].Key)) < 0.017453292519943)
                    move.FireType = FireType.Premium;
            }

            if (bestAngle != tmpAngle)
            {
                double tmpTurnAngle = Mathematics.AngleDifference(tmpAngle, bestAngle);

                move.TurretTurn = tmpTurnAngle;
                //move.LeftTrackPower = tmpTurnAngle > 0 ? Self.EngineRearPowerFactor : -1;
                //move.RightTrackPower = tmpTurnAngle < 0 ? Self.EngineRearPowerFactor : -1;
            }

            // DRIVING -------------------------------------------------------------------------------------------------------------

            double bestBonusDistance = 10000, tmpDistance;
            Point bestBonusLocation = new Point(), tmpBonusLocation;
            bool haveTarget = false;

            foreach (Bonus tmpBonus in World.Bonuses)
            {
                tmpBonusLocation = new Point(tmpBonus.X, tmpBonus.Y);
                tmpDistance = Mathematics.Distance(SelfLocation, tmpBonusLocation);

                if ((_currentBonus != null && tmpBonus.Id == _currentBonus.Id) || tmpDistance < bestBonusDistance)
                {
                    haveTarget = true;
                    _currentBonus = tmpBonus;
                    bestBonusDistance = tmpDistance;
                    bestBonusLocation = tmpBonusLocation;
                }
            }

            if (!haveTarget)
            {
                _currentBonus = null;
                return;
            }

            //double movementAngle = TankPlus[Self.Id].AnglePrediction[0];
            double turnAngle = Mathematics.AngleDifference(Self.Angle, Mathematics.SegmentAngle(SelfLocation, bestBonusLocation));

            if (Math.Abs(turnAngle) < Mathematics.SixthPI)
            {
                move.LeftTrackPower = 1;
                move.RightTrackPower = 1;
            }
            else
            {
                move.TurretTurn = turnAngle;
                move.LeftTrackPower = turnAngle > 0 ? 0.75 : -1;
                move.RightTrackPower = turnAngle < 0 ? 0.75 : -1;
            }

            /*double turnRatio = (Math.PI * Math.PI - Math.Abs(turnAngle * turnAngle)) / (Math.PI * Math.PI);

            if (Math.Abs(turnAngle) < Mathematics.HalfPI)
            {
                turnRatio /= 2;

                if (Math.Abs(turnAngle) < Mathematics.ThirdPI)
                {
                    turnRatio /= 2;

                    if (Math.Abs(turnAngle) < Mathematics.SixthPI)
                    {
                        turnRatio /= 2;
                    }
                }
            }

            move.LeftTrackPower = turnAngle > 0 ? 1 - 0.25 * turnRatio : 1 - 2 * turnRatio;
            move.RightTrackPower = turnAngle < 0 ? 1 - 0.25 * turnRatio : 1 - 2 * turnRatio;*/

            //double tmpAngle = Mathematics.AddAnglesAbsolute(Self.Angle, Self.TurretRelativeAngle);
            //Point tmpLocation = new Point(Self.X, Self.Y);
            //List<KeyValuePair<double, double>> tmpAnglesRegular = new List<KeyValuePair<double, double>>();
            //List<KeyValuePair<double, double>> tmpAnglesPremium = new List<KeyValuePair<double, double>>();

            //for (int i = -Settings.PredictionAngleCount; i < Settings.PredictionAngleCount; ++i)
            //{
            //    double tmpTestAngle = Mathematics.AddAnglesAbsolute(tmpAngle, Settings.PredictionAngleStep * i);
            //    tmpAnglesRegular.Add(new KeyValuePair<double, double>(tmpTestAngle, CalculateValue(tmpLocation, tmpTestAngle, ShellType.Regular)));
            //    //tmpAnglesPremium.Add(new KeyValuePair<double, double>(tmpTestAngle, CalculateValue(tmpLocation, tmpTestAngle, ShellType.Premium)));
            //}

            //tmpAnglesRegular.Sort((Comparison<KeyValuePair<double, double>>)((x, y) => y.Value.CompareTo(x.Value)));
            ////tmpAnglesPremium.Sort((Comparison<KeyValuePair<double, double>>)((x, y) => y.Value.CompareTo(x.Value)));

            //if (tmpAnglesRegular[0].Key == tmpAngle && tmpAnglesRegular[0].Value > Settings.RegularLimit)
            //    move.FireType = FireType.Regular;

            ////if (Self.PremiumShellCount > 0 && tmpAnglesPremium[0].Key == tmpAngle && tmpAnglesPremium[0].Value > Settings.RegularLimit)
            ////    move.FireType = FireType.Premium;

            //if (move.FireType == null && tmpAnglesRegular[0].Value > 0)
            //{
            //    double tmpTurnAngle = tmpAnglesRegular[0].Key;

            //    //if (tmpAnglesRegular[0].Value > tmpAnglesPremium[0].Value)
            //    //    tmpTurnAngle = tmpAnglesRegular[0].Key;
            //    //else
            //    //    tmpTurnAngle = tmpAnglesPremium[0].Key;

            //    move.TurretTurn = Mathematics.AddAnglesAbsolute(tmpTurnAngle, -tmpAngle);
            //    move.LeftTrackPower = move.TurretTurn > 0 ? Self.EngineRearPowerFactor : -1;
            //    move.RightTrackPower = move.TurretTurn < 0 ? Self.EngineRearPowerFactor : -1;
            //}

            ////Lap("A");
            ////Console.ReadLine();
        }

        //private double CalculateValue(Point A, double angle, ShellType shellType)
        //{
        //    double tmpCancel = Settings.SafeMaxTicks, tmpTicks, tmpValue = 0;

        //    //foreach (BonusPlus tmpBonusPlus in BonusPlus.Values)
        //    //    if ((tmpTicks = tmpBonusPlus.Collision(A, angle, shellType)) >= 0 && tmpTicks < tmpCancel)
        //    //        tmpCancel = tmpTicks; // FIX GODDAMN INTERSECTION ALGO

        //    List<TankCollision> tmpCollisionList;

        //    foreach (TankPlus tmpTankPlus in TankPlus.Values)
        //        if (!tmpTankPlus.IsAlive && (tmpCollisionList = tmpTankPlus.Collision(A, angle, shellType)).Count > 0)
        //            foreach (TankCollision tmpTankColission in tmpCollisionList)
        //                if (tmpTankColission.Collision.Ticks < tmpCancel)
        //                    tmpCancel = tmpTankColission.Collision.Ticks;

        //    foreach (TankPlus tmpTankPlus in TankPlus.Values)
        //        if (tmpTankPlus.IsAlive && tmpTankPlus.Tank != Self && (tmpCollisionList = tmpTankPlus.Collision(A, angle, shellType)).Count > 0)
        //            foreach (TankCollision tmpTankColission in tmpCollisionList)
        //                if (tmpTankColission.Collision.Ticks < tmpCancel)
        //                {
        //                    var tmpDeltaAngle = Math.Abs(Mathematics.AddAnglesAbsolute(angle, -(Self.Angle + Self.TurretRelativeAngle)));
        //                    tmpValue += 1 / tmpTankColission.Collision.Ticks - tmpDeltaAngle / (Math.PI * 10) - (Math.Abs(tmpTankColission.Collision.NormalAngle) > (Math.PI / 3) ? 1 : 0); // MAGIC HAPPENS HERE
        //                }

        //    //Console.ReadLine();

        //    return tmpValue;
        //}

        public TankType SelectTank(int tankIndex, int teamSize)
        {
            return TankType;
        }
    }
}
