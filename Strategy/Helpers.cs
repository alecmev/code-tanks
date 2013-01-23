using System;
using Com.CodeGame.CodeTanks2012.DevKit.CSharpCgdk.Model;

namespace Com.CodeGame.CodeTanks2012.DevKit.CSharpCgdk
{
    public static class Helpers
    {
        public static double ShellSpeed(ShellType shellType, double ticks)
        {
            return MyStrategy.ShellProperties[shellType].InitialSpeed * Math.Exp(MyStrategy.ShellProperties[shellType].SpeedPowerRatio * ticks);
        }

        public static double ShellDistance(ShellType shellType, double ticks)
        {
            return (MyStrategy.ShellProperties[shellType].InitialSpeed * (Math.Exp(MyStrategy.ShellProperties[shellType].SpeedPowerRatio * ticks) - 1)) / MyStrategy.ShellProperties[shellType].SpeedPowerRatio;
        }

        public static double ShellTicks(ShellType shellType, double distance)
        {
            return distance > 0 ? Mathematics.Newton(
                    t => (MyStrategy.ShellProperties[shellType].InitialSpeed * (Math.Exp(MyStrategy.ShellProperties[shellType].SpeedPowerRatio * t) - 1)) / MyStrategy.ShellProperties[shellType].SpeedPowerRatio, // integral of speed
                    t => MyStrategy.ShellProperties[shellType].InitialSpeed * Math.Exp(MyStrategy.ShellProperties[shellType].SpeedPowerRatio * t), // speed
                    distance, Settings.TickApproximationEpsilon) : 0;
        }
    }
}
