using System;
using Com.CodeGame.CodeTanks2012.DevKit.CSharpCgdk.Model;

namespace Com.CodeGame.CodeTanks2012.DevKit.CSharpCgdk
{
    //public class Surface
    //{
    //    public Line Line { get; private set; }
    //    public int Tick { get; private set; }

    //    public Surface(Line line, int tick)
    //    {
    //        Line = line;
    //        Tick = tick;
    //    }

    //    public Collision Collision(Point A, double angle, ShellType shellType)
    //    {
    //        double tmpIncidentAngle;

    //        if (!Mathematics.SegmentVisibility(A, Line.A, Line.B, out tmpIncidentAngle))
    //            return null;

    //        Point tmpIntersection;

    //        if (!Mathematics.DoSegmentsIntersect(A, new Point(A.X + Math.Cos(angle) * Settings.SafeMaxLength, A.Y + Math.Sin(angle) * Settings.SafeMaxLength), Line.A, Line.B, out tmpIntersection))
    //            return null;

    //        double tmpDistance = Mathematics.Distance(A, tmpIntersection) - MyStrategy.TankProperties[MyStrategy.TankType].TurrentLength;
    //        ShellProperties tmpProperties = MyStrategy.ShellProperties[shellType];
    //        double tmpTicks = 0;

    //        if (tmpDistance > 0)
    //            tmpTicks = Mathematics.Newton(
    //                t => (MyStrategy.ShellProperties[shellType].InitialSpeed * (Math.Exp(MyStrategy.ShellProperties[shellType].SpeedPowerRatio * t) - 1)) / MyStrategy.ShellProperties[shellType].SpeedPowerRatio, // integral of speed
    //                t => MyStrategy.ShellProperties[shellType].InitialSpeed * Math.Exp(MyStrategy.ShellProperties[shellType].SpeedPowerRatio * t), // speed
    //                tmpDistance, Settings.TickApproximationEpsilon);

    //        if (Tick >= 0 && Math.Abs(Tick - tmpTicks) > Settings.TickPrecision) // if Tick < 0 - then this is a permanent surface
    //            return null;

    //        return new Collision(tmpTicks, tmpProperties.InitialSpeed * Math.Pow(Math.E, tmpProperties.SpeedPowerRatio * tmpTicks), tmpIncidentAngle - Mathematics.HalfPI);
    //    }
    //}

    //public class Collision
    //{
    //    public double Ticks { get; private set; }
    //    public double Speed { get; private set; }
    //    public double NormalAngle { get; private set; }

    //    public Collision(double ticks, double speed, double normalAngle)
    //    {
    //        Ticks = ticks;
    //        Speed = speed;
    //        NormalAngle = normalAngle;
    //    }
    //}
}
