using System;
using System.Collections.Generic;
using Com.CodeGame.CodeTanks2012.DevKit.CSharpCgdk.Model;

namespace Com.CodeGame.CodeTanks2012.DevKit.CSharpCgdk
{
    public sealed class MyStrategy : IStrategy
    {
        private Queue<double> _angles = new Queue<double>();
        private double _previousAngle = -100;
        private double _previousDelta = -100;
        private double _maxDelta = -1;

        public void Move(Tank self, World world, Move move)
        {
            Tank tmpGuy = null;

            foreach (Tank tmpTank in world.Tanks)
                if (tmpTank.PlayerName == "QuickStartGuy")
                {
                    tmpGuy = tmpTank;
                    break;
                }

            double tmpAngle = self.GetAngleTo(tmpGuy) - self.TurretRelativeAngle;
            move.TurretTurn = tmpAngle;

            if (Math.Abs(tmpAngle) < 0.01)
                move.FireType = FireType.PremiumPreferred;
            else if (self.GetAngleTo(tmpGuy) > 0)
            {
                move.LeftTrackPower = self.EngineRearPowerFactor;
                move.RightTrackPower = -1.0;
            }
            else
            {
                move.LeftTrackPower = -1.0;
                move.RightTrackPower = self.EngineRearPowerFactor;
            }
        }

        public TankType SelectTank(int tankIndex, int teamSize)
        {
            return TankType.Medium;
        }
    }
}
