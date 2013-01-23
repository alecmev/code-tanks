using System;

namespace Com.CodeGame.CodeTanks2012.DevKit.CSharpCgdk.Model
{
    public sealed class Tank : Unit
    {
        private readonly string playerName;
        private readonly int teammateIndex;
        private readonly double turretRelativeAngle;
        private readonly int crewHealth;
        private readonly int hullDurability;
        private readonly int reloadingTime;
        private readonly int remainingReloadingTime;
        private readonly int premiumShellCount;
        private readonly bool isTeammate;
        private readonly TankType type;

        public Tank(long id, string playerName, int teammateIndex,
            double x, double y, double speedX, double speedY, double angle, double angularSpeed,
            double turretRelativeAngle, int crewHealth, int hullDurability,
            int reloadingTime, int remainingReloadingTime, int premiumShellCount, bool isTeammate, TankType type)
            : base(id, GetWidth(type), GetHeight(type), x, y, speedX, speedY, angle, angularSpeed)
        {
            this.playerName = playerName;
            this.teammateIndex = teammateIndex;
            this.turretRelativeAngle = turretRelativeAngle;
            this.crewHealth = crewHealth;
            this.hullDurability = hullDurability;
            this.reloadingTime = reloadingTime;
            this.remainingReloadingTime = remainingReloadingTime;
            this.premiumShellCount = premiumShellCount;
            this.isTeammate = isTeammate;
            this.type = type;
        }

        private static double GetWidth(TankType type)
        {
            switch (type)
            {
                case TankType.Medium:
                    return 90.0D;
                case TankType.Heavy:
                    return 105.0D;
                case TankType.TankDestroyer:
                    return 112.5D;
                default:
                    throw new ArgumentException();
            }
        }

        private static double GetHeight(TankType type)
        {
            switch (type)
            {
                case TankType.Medium:
                    return 60.0D;
                case TankType.Heavy:
                    return 75.0D;
                case TankType.TankDestroyer:
                    return 67.5D;
                default:
                    throw new ArgumentException();
            }
        }

        public string PlayerName
        {
            get { return playerName; }
        }

        public int TeammateIndex
        {
            get { return teammateIndex; }
        }

        public double TurretRelativeAngle
        {
            get { return turretRelativeAngle; }
        }

        public int CrewHealth
        {
            get { return crewHealth; }
        }

        public int HullDurability
        {
            get { return hullDurability; }
        }

        public int ReloadingTime
        {
            get { return reloadingTime; }
        }

        public int RemainingReloadingTime
        {
            get { return remainingReloadingTime; }
        }

        public int PremiumShellCount
        {
            get { return premiumShellCount; }
        }

        public bool IsTeammate
        {
            get { return isTeammate; }
        }

        public TankType Type
        {
            get { return type; }
        }

        public double VirtualGunLength
        {
            get
            {
                switch (type)
                {
                    case TankType.Medium:
                        return 67.5D;
                    case TankType.Heavy:
                        return 82.5D;
                    case TankType.TankDestroyer:
                        return 97.5D;
                    default:
                        throw new ArgumentException();
                }
            }
        }

        public double Mass
        {
            get
            {
                switch (type)
                {
                    case TankType.Medium:
                        return 10.0D;
                    case TankType.Heavy:
                        return 20.0D;
                    case TankType.TankDestroyer:
                        return 15.0D;
                    default:
                        throw new ArgumentException();
                }
            }
        }

        public double EnginePower
        {
            get
            {
                switch (type)
                {
                    case TankType.Medium:
                        return 7500.0D;
                    case TankType.Heavy:
                        return 7500.0D;
                    case TankType.TankDestroyer:
                        return 5000.0D;
                    default:
                        throw new ArgumentException();
                }
            }
        }

        public double EngineRearPowerFactor
        {
            get
            {
                switch (type)
                {
                    case TankType.Medium:
                        return 0.75D;
                    case TankType.Heavy:
                        return 0.5D;
                    case TankType.TankDestroyer:
                        return 0.35D;
                    default:
                        throw new ArgumentException();
                }
            }
        }

        public double TurretTurnSpeed
        {
            get
            {
                switch (type)
                {
                    case TankType.Medium:
                        return 1.0D * Math.PI / 180.0D;
                    case TankType.Heavy:
                        return 0.5D * Math.PI / 180.0D;
                    case TankType.TankDestroyer:
                        return 1.5D * Math.PI / 180.0D;
                    default:
                        throw new ArgumentException();
                }
            }
        }

        public double TurretMaxRelativeAngle
        {
            get
            {
                switch (type)
                {
                    case TankType.Medium:
                        return 0.0D * Math.PI / 180.0D;
                    case TankType.Heavy:
                        return 0.0D * Math.PI / 180.0D;
                    case TankType.TankDestroyer:
                        return 15.0D * Math.PI / 180.0D;
                    default:
                        throw new ArgumentException();
                }
            }
        }

        public int CrewMaxHealth
        {
            get
            {
                switch (type)
                {
                    case TankType.Medium:
                        return 100;
                    case TankType.Heavy:
                        return 100;
                    case TankType.TankDestroyer:
                        return 100;
                    default:
                        throw new ArgumentException();
                }
            }
        }

        public int HullMaxDurability
        {
            get
            {
                switch (type)
                {
                    case TankType.Medium:
                        return 200;
                    case TankType.Heavy:
                        return 250;
                    case TankType.TankDestroyer:
                        return 250;
                    default:
                        throw new ArgumentException();
                }
            }
        }

        public int FrontalArmor
        {
            get
            {
                switch (type)
                {
                    case TankType.Medium:
                        return 175;
                    case TankType.Heavy:
                        return 200;
                    case TankType.TankDestroyer:
                        return 250;
                    default:
                        throw new ArgumentException();
                }
            }
        }

        public int SideArmor
        {
            get
            {
                switch (type)
                {
                    case TankType.Medium:
                        return 150;
                    case TankType.Heavy:
                        return 175;
                    case TankType.TankDestroyer:
                        return 125;
                    default:
                        throw new ArgumentException();
                }
            }
        }

        public int RearArmor
        {
            get
            {
                switch (type)
                {
                    case TankType.Medium:
                        return 100;
                    case TankType.Heavy:
                        return 100;
                    case TankType.TankDestroyer:
                        return 100;
                    default:
                        throw new ArgumentException();
                }
            }
        }

        /// <summary>
        /// Returns the relative angle in range of -PI to PI both inclusive.
        /// </summary>
        /// <param name="x">The abscissa coordinate of the point.</param>
        /// <param name="y">The ordinate coordinate of the point.</param>
        /// <returns>Angle to the point relative to this tank turret's angle.</returns>
        public double GetTurretAngleTo(double x, double y)
        {
            double absoluteAngleTo = Math.Atan2(y - Y, x - X);
            double relativeAngleTo = absoluteAngleTo - Angle - turretRelativeAngle;

            while (relativeAngleTo > Math.PI)
            {
                relativeAngleTo -= 2.0D * Math.PI;
            }

            while (relativeAngleTo < -Math.PI)
            {
                relativeAngleTo += 2.0D * Math.PI;
            }

            return relativeAngleTo;
        }

        /// <summary>
        /// Returns the relative angle in range of -PI to PI both inclusive.
        /// </summary>
        /// <param name="unit">Unit to calculate turret relative angle to.</param>
        /// <returns>Angle to the center of specified unit relative to this tank turret's angle.</returns>
        public double GetTurretAngleTo(Unit unit)
        {
            return GetTurretAngleTo(unit.X, unit.Y);
        }
    }
}
