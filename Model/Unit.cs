using System;

namespace Com.CodeGame.CodeTanks2012.DevKit.CSharpCgdk.Model
{
    public abstract class Unit
    {
        private readonly long id;
        private readonly double width;
        private readonly double height;
        private readonly double x;
        private readonly double y;
        private readonly double speedX;
        private readonly double speedY;
        private readonly double angle;
        private readonly double angularSpeed;

        protected Unit(long id, double width, double height, double x, double y, double speedX, double speedY, double angle, double angularSpeed)
        {
            this.id = id;
            this.width = width;
            this.height = height;
            this.x = x;
            this.y = y;
            this.speedX = speedX;
            this.speedY = speedY;
            this.angle = angle;
            this.angularSpeed = angularSpeed;
        }

        public long Id
        {
            get { return id; }
        }

        public double Width
        {
            get { return width; }
        }

        public double Height
        {
            get { return height; }
        }

        public double X
        {
            get { return x; }
        }

        public double Y
        {
            get { return y; }
        }

        public double SpeedX
        {
            get { return speedX; }
        }

        public double SpeedY
        {
            get { return speedY; }
        }

        public double Angle
        {
            get { return angle; }
        }

        public double AngularSpeed
        {
            get { return angularSpeed; }
        }

        /// <summary>
        /// Returns the relative angle in range of -PI to PI both inclusive.
        /// </summary>
        /// <param name="x">The abscissa coordinate of the point.</param>
        /// <param name="y">The ordinate coordinate of the point.</param>
        /// <returns>Angle to the point relative to this unit's angle.</returns>
        public double GetAngleTo(double x, double y)
        {
            double absoluteAngleTo = Math.Atan2(y - this.y, x - this.x);
            double relativeAngleTo = absoluteAngleTo - angle;

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
        /// <param name="unit">Unit to calculate relative angle to.</param>
        /// <returns>Angle to the center of specified unit relative to this unit's angle.</returns>
        public double GetAngleTo(Unit unit)
        {
            return GetAngleTo(unit.x, unit.y);
        }

        /// <summary>
        /// Returns the distance to the point.
        /// </summary>
        /// <param name="x">The abscissa coordinate of the point.</param>
        /// <param name="y">The ordinate coordinate of the point.</param>
        /// <returns>Distance to the point from the center of the unit.</returns>
        public double GetDistanceTo(double x, double y)
        {
            double xRange = (x - this.x);
            double yRange = (y - this.y);
            return Math.Sqrt(xRange * xRange + yRange * yRange);
        }

        /// <summary>
        /// Returns the distance to the unit's center.
        /// </summary>
        /// <param name="unit">Unit to calculate distance to.</param>
        /// <returns>Distance to the center of specified unit from the center of this unit.</returns>
        public double GetDistanceTo(Unit unit)
        {
            return GetDistanceTo(unit.x, unit.y);
        }
    }
}
