using System;
using System.Collections.Generic;
using Com.CodeGame.CodeTanks2012.DevKit.CSharpCgdk.Model;

namespace Com.CodeGame.CodeTanks2012.DevKit.CSharpCgdk
{
    public class TankPlus
    {
        public long Id { get; private set; }
        public Tank Tank { get { return MyStrategy.Tanks[Id]; } }
        public TankProperties Properties { get { return MyStrategy.TankProperties[Tank.Type]; } }
        public bool IsAlive { get { return Tank.CrewHealth > 0 && Tank.HullDurability > 0; } }

        public LimitedQueue<double>[] AngleHistory = new LimitedQueue<double>[Settings.BufferSize];
        public LimitedQueue<double>[] SpeedHistory = new LimitedQueue<double>[Settings.BufferSize];
        public LimitedQueue<double>[] OrientationHistory = new LimitedQueue<double>[Settings.BufferSize];

        public double[] AnglePrediction = new double[Settings.PredictionLength];
        public double[] SpeedPrediction = new double[Settings.PredictionLength];
        public double[] OrientationPrediction = new double[Settings.PredictionLength];
        public Point[] LocationPrediction = new Point[Settings.PredictionLength];
        //public Surface[][] SurfacePrediction = new Surface[Settings.PredictionLength][];

        public TankPlus(long id)
        {
            Id = id;

            for (int i = 0; i < Settings.BufferSize; ++i)
            {
                AngleHistory[i] = new LimitedQueue<double>(Settings.BufferSize - i, 0);
                SpeedHistory[i] = new LimitedQueue<double>(Settings.BufferSize - i, 0);
                OrientationHistory[i] = new LimitedQueue<double>(Settings.BufferSize - i, 0);
            }

            //for (int i = 0; i < Settings.PredictionLength; ++i)
            //    SurfacePrediction[i] = new Surface[4];
        }

        public void Update()
        {
            AngleHistory[0].Enqueue(MovementAngle(Tank));
            SpeedHistory[0].Enqueue(MovementSpeed(Tank));
            OrientationHistory[0].Enqueue(Tank.Angle);

            UpdateComponents(ref AngleHistory, ref AnglePrediction);
            UpdateComponents(ref SpeedHistory, ref SpeedPrediction);
            UpdateComponents(ref OrientationHistory, ref OrientationPrediction);

            LocationPrediction[0] = new Point(Tank.X, Tank.Y);
            UpdateSurfaces(ShellType.Regular, 0);
            UpdateSurfaces(ShellType.Premium, 0);

            for (int i = 1; i < Settings.PredictionLength; ++i)
            {
                LocationPrediction[i] = new Point(
                    LocationPrediction[i - 1].X + Math.Cos(AnglePrediction[i]) * SpeedPrediction[i],
                    LocationPrediction[i - 1].Y + Math.Sin(AnglePrediction[i]) * SpeedPrediction[i]);
                UpdateSurfaces(ShellType.Regular, i);
                UpdateSurfaces(ShellType.Premium, i);
            }
        }

        private void UpdateComponents(ref LimitedQueue<double>[] history, ref double[] valuePrediction)
        {
            for (int i = 1; i < Settings.BufferSize; ++i)
                history[i].Enqueue(history[i - 1].Last - history[i - 1].Penultimate);

            double[] tmpValues = new double[Settings.BufferSize];
            int tmpBufferSizeMinusOne = Settings.BufferSize - 1;

            for (int i = 0; i < tmpBufferSizeMinusOne; ++i)
            {
                double tmpSum = 0;

                foreach (double tmpValue in history[i])
                    tmpSum += tmpValue;

                tmpValues[i] = tmpSum / history[i].Limit;
            }

            tmpValues[tmpBufferSizeMinusOne] = 0;

            for (int i = 0; i < Settings.PredictionLength; ++i)
            {
                valuePrediction[i] = tmpValues[0];

                for (int j = tmpBufferSizeMinusOne; j > 0; --j)
                    tmpValues[j - 1] += tmpValues[j];
            }
        }

        private void UpdateSurfaces(ShellType shellType, int tick)
        {
            if (!IsAlive)
                return;

            double tmpShellDistance = Helpers.ShellDistance(shellType, tick);
            double tmpDistance = Mathematics.Distance(MyStrategy.SelfLocation, LocationPrediction[tick]) - MyStrategy.Properties.TurrentLength;

            if (Math.Atan(tmpDistance - tmpShellDistance) > Properties.HalfDiagonal)
                return;

            Point tmpFL, tmpFR, tmpRL, tmpRR;
            double tmpAAngle, tmpBAngle;

            tmpAAngle = OrientationPrediction[tick] - Properties.CentralAngle;
            tmpFL = new Point(LocationPrediction[tick].X + Math.Cos(tmpAAngle) * Properties.HalfDiagonal, LocationPrediction[tick].Y + Math.Sin(tmpAAngle) * Properties.HalfDiagonal);
            tmpRR = new Point(LocationPrediction[tick].X - Math.Cos(tmpAAngle) * Properties.HalfDiagonal, LocationPrediction[tick].Y - Math.Sin(tmpAAngle) * Properties.HalfDiagonal);

            tmpBAngle = OrientationPrediction[tick] + Properties.CentralAngle;
            tmpFR = new Point(LocationPrediction[tick].X + Math.Cos(tmpBAngle) * Properties.HalfDiagonal, LocationPrediction[tick].Y + Math.Sin(tmpBAngle) * Properties.HalfDiagonal);
            tmpRL = new Point(LocationPrediction[tick].X - Math.Cos(tmpBAngle) * Properties.HalfDiagonal, LocationPrediction[tick].Y - Math.Sin(tmpBAngle) * Properties.HalfDiagonal);

            HandleSurface(shellType, tick, Properties.ArmorProperties[ArmorType.Front], tmpFL, tmpFR);
            HandleSurface(shellType, tick, Properties.ArmorProperties[ArmorType.Side], tmpRL, tmpFL);
            HandleSurface(shellType, tick, Properties.ArmorProperties[ArmorType.Side], tmpFR, tmpRR);
            HandleSurface(shellType, tick, Properties.ArmorProperties[ArmorType.Rear], tmpRR, tmpRL);
        }

        private void HandleSurface(ShellType shellType, int tick, int armor, Point A, Point B)
        {
            double tmpAngleToA = Mathematics.SegmentAngle(MyStrategy.SelfLocation, A);
            double tmpAngleToB = Mathematics.SegmentAngle(MyStrategy.SelfLocation, B);

            if (Mathematics.AngleDifference(tmpAngleToA, tmpAngleToB) >= 0) // it's just ">=" because image of the AngleDifference is (-PI, PI]
                return;

            double tmpAlpha = Mathematics.HalfPI - Mathematics.AngleBetweenSegments(A, B, MyStrategy.SelfLocation, A);
            double tmpBeta = Mathematics.HalfPI - Mathematics.AngleBetweenSegments(A, B, MyStrategy.SelfLocation, B);

            if (shellType == ShellType.Regular)
            {
                if (Math.Abs(tmpAlpha) < Mathematics.ThirdPI)
                {
                    if (Math.Abs(tmpBeta) >= Mathematics.ThirdPI) // beta should be corrected
                    {
                        tmpAngleToB = CorrectAngle(tmpAngleToB, tmpBeta, false);
                        tmpBeta = Mathematics.ThirdPI * Math.Sign(tmpBeta);
                    }
                }
                else
                {
                    if (Math.Abs(tmpBeta) < Mathematics.ThirdPI) // alpha should be corrected
                    {
                        tmpAngleToA = CorrectAngle(tmpAngleToA, tmpAlpha, true);
                        tmpAlpha = Mathematics.ThirdPI * Math.Sign(tmpAlpha);
                    }
                    else
                    {
                        if (Math.Sign(tmpAlpha) == Math.Sign(tmpBeta)) // any shot will result in a reicochete
                            return;
                        else // both alpha and beta should be corrected
                        {
                            tmpAngleToA = CorrectAngle(tmpAngleToA, tmpAlpha, true);
                            tmpAlpha = Mathematics.ThirdPI * Math.Sign(tmpAlpha);
                            tmpAngleToB = CorrectAngle(tmpAngleToB, tmpBeta, false);
                            tmpBeta = Mathematics.ThirdPI * Math.Sign(tmpBeta);
                        }
                    }
                }
            }

            double resultAngle = Mathematics.AngleBisection(tmpAngleToA, tmpAngleToB), resultValue = 0, bestIncidentAngle = 0;

            if (Math.Sign(tmpAlpha) == Math.Sign(tmpBeta))
                bestIncidentAngle = Math.Min(Math.Abs(tmpAlpha), Math.Abs(tmpBeta));

            double tmpShellSpeed = Helpers.ShellSpeed(shellType, tick);
            double tmpArmorPenetration = tmpShellSpeed * MyStrategy.ShellProperties[shellType].ArmorPenetration / MyStrategy.ShellProperties[shellType].InitialSpeed;
            double tmpArmorThickness = armor / Math.Cos(bestIncidentAngle);

            if (tmpArmorPenetration >= tmpArmorThickness)
            {
                resultValue += MyStrategy.ShellProperties[shellType].Damage;

                if (Tank.CrewHealth <= MyStrategy.ShellProperties[shellType].Damage)
                    resultValue += 25;
            }

            if (Tank.HullDurability <= MyStrategy.ShellProperties[shellType].Damage) // 25 is added INTENTIONALLY
                resultValue += 25;

            resultValue *= Math.Abs(Mathematics.AngleDifference(tmpAngleToA, tmpAngleToB)) / Math.PI;
            resultValue *= (Mathematics.HalfPI * 2 - bestIncidentAngle) / (Mathematics.HalfPI * 2);
            resultValue *= (Settings.PredictionLength * 2 - tick) / (Settings.PredictionLength * 2);
            resultValue *= (Properties.MaxSpeed * 5 - (SpeedPrediction[tick] < Properties.MaxSpeed ? SpeedPrediction[tick] : Properties.MaxSpeed)) / (Properties.MaxSpeed * 5);
            resultValue *= (Math.PI * 2 - Math.Abs(Mathematics.AngleDifference(MyStrategy.Self.Angle + MyStrategy.Self.TurretRelativeAngle, resultAngle))) / (Math.PI * 2);

            if (shellType == ShellType.Regular)
                MyStrategy.AnglesRegular.Add(new KeyValuePair<double, double>(resultAngle, resultValue));
            else
                MyStrategy.AnglesPremium.Add(new KeyValuePair<double, double>(resultAngle, resultValue));
        }

        private double CorrectAngle(double angle, double incidentAngle, bool isA)
        {
            return angle + (isA ? -1 : 1) * (incidentAngle - Mathematics.ThirdPI);
        }

        //public List<TankCollision> Collision(Point A, double angle, ShellType shellType)
        //{
        //    Collision tmpCollision;
        //    List<TankCollision> tmpResult = new List<TankCollision>();

        //    for (int tick = 0; tick < Settings.PredictionLength; ++tick)
        //        for (int i = 0; i < 4; ++i)
        //            if ((tmpCollision = SurfacePrediction[tick][i].Collision(A, angle, shellType)) != null)
        //            {
        //                tmpResult.Add(new TankCollision(tmpCollision, i == 0 ? Properties.ArmorProperties[ArmorType.Front] : (i == 3 ? Properties.ArmorProperties[ArmorType.Rear] : Properties.ArmorProperties[ArmorType.Side])));
        //                break;
        //            }

        //    return tmpResult;
        //}

        public static double MovementAngle(Unit unit)
        {
            return Mathematics.VectorAngle(unit.SpeedX, unit.SpeedY);
        }

        public static double MovementSpeed(Unit unit)
        {
            return Mathematics.Pythagor(unit.SpeedX, unit.SpeedY);
        }
    }

    //public class TankCollision
    //{
    //    public Collision Collision { get; private set; }
    //    public int Armor { get; private set; }

    //    public TankCollision(Collision collision, int armor)
    //    {
    //        Collision = collision;
    //        Armor = armor;
    //    }
    //}
}
