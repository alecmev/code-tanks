using System;
using Com.CodeGame.CodeTanks2012.DevKit.CSharpCgdk.Model;

namespace Com.CodeGame.CodeTanks2012.DevKit.CSharpCgdk
{
    public static class Mathematics
    {
        public const int MaxIterations = 256;
        public const double SixthPI = Math.PI / 6;
        public const double ThirdPI = Math.PI / 3;
        public const double HalfPI = Math.PI / 2;
        public const double TwoPI = Math.PI * 2;

        public static double Newton(Func<double, double> f, Func<double, double> fd, double desiredY, double epsilon)
        {
            epsilon = Math.Abs(epsilon);
            double tmpX = 0, tmpY = f(tmpX), tmpFd;

            for (int i = 0; i < MaxIterations && Math.Abs(tmpY - desiredY) > epsilon; ++i)
            {
                tmpFd = fd(tmpX);
                tmpX += tmpFd != 0 ? (desiredY - tmpY) / tmpFd : 1;
                tmpY = f(tmpX);
            }

            return tmpX;
        }

        //public static bool DoSegmentsIntersect(Point A1, Point A2, Point B1, Point B2, out Point location)
        //{
        //    location = new Point();
        //    double tmpLeftA, tmpLeftB, tmpRightA, tmpRightB;

        //    if (A2.X > A1.X)
        //    {
        //        tmpLeftA = A1.X;
        //        tmpRightA = A2.X;
        //    }
        //    else
        //    {
        //        tmpLeftA = A2.X;
        //        tmpRightA = A1.X;
        //    }

        //    if (B2.X > B1.X)
        //    {
        //        tmpLeftB = B1.X;
        //        tmpRightB = B2.X;
        //    }
        //    else
        //    {
        //        tmpLeftB = B2.X;
        //        tmpRightB = B1.X;
        //    }

        //    double tmpTopA, tmpTopB, tmpBottomA, tmpBottomB;

        //    if (A2.Y > A1.Y)
        //    {
        //        tmpTopA = A1.Y;
        //        tmpBottomA = A2.Y;
        //    }
        //    else
        //    {
        //        tmpTopA = A2.Y;
        //        tmpBottomA = A1.Y;
        //    }

        //    if (B2.Y > B1.Y)
        //    {
        //        tmpTopB = B1.Y;
        //        tmpBottomB = B2.Y;
        //    }
        //    else
        //    {
        //        tmpTopB = B2.Y;
        //        tmpBottomB = B1.Y;
        //    }

        //    if (tmpLeftA > tmpRightB || tmpLeftB > tmpRightA || tmpTopA > tmpBottomB || tmpTopB > tmpBottomA)
        //        return false;

        //    double tmpA = (A2.Y - A1.Y) / (A2.X - A1.X);
        //    double tmpB = (B2.Y - B1.Y) / (B2.X - B1.X);

        //    if (tmpA == tmpB)
        //        return false;

        //    if (A1.X == A2.X)
        //        location.X = A1.X;
        //    else if (B1.X == B2.X)
        //        location.X = B1.X;
        //    else
        //        location.X = (B1.Y - A1.Y - B1.X * tmpB + A1.X * tmpA) / (tmpA - tmpB);

        //    double tmpLeft = (tmpLeftB > tmpLeftA ? tmpLeftB : tmpLeftA);
        //    double tmpRight = (tmpRightB < tmpRightA ? tmpRightB : tmpRightA);
        //    double tmpTop = (tmpTopB < tmpTopA ? tmpTopB : tmpTopA);
        //    double tmpBottom = (tmpBottomB > tmpBottomA ? tmpBottomB : tmpBottomA);

        //    if (A1.X != A2.X)
        //        location.Y = A1.Y + tmpA * (location.X - A1.X);
        //    else
        //        location.Y = B1.Y + tmpB * (location.X - B1.X);

        //    if (location.X == double.NaN || location.Y == double.NaN)
        //        Console.WriteLine("NaN");

        //    if (location.X == double.NaN || location.Y == double.NaN || location.X < tmpLeft || location.X > tmpRight || location.Y < tmpTop || location.Y > tmpBottom)
        //        return false;

        //    return true;
        //}

        /// <summary>
        /// Minimal positive angle.
        /// </summary>
        /// <param name="A">Angle.</param>
        /// <returns>Angle in [0, TwoPI).</returns>
        public static double NormalizeAnglePositive(double A)
        {
            while (A < 0)
                A += TwoPI;

            while (A >= TwoPI)
                A -= TwoPI;

            return A;
        }

        /// <summary>
        /// Minimal positive sum of two angles.
        /// </summary>
        /// <param name="A">First.</param>
        /// <param name="B">Second.</param>
        /// <returns>Angle in [0, TwoPI).</returns>
        public static double AddAnglesPositive(double A, double B)
        {
            return NormalizeAnglePositive(A + B);
        }

        /// <summary>
        /// Minimal absolute angle difference from A to B.
        /// </summary>
        /// <param name="A">From.</param>
        /// <param name="B">To.</param>
        /// <returns>Angle in (-PI, PI].</returns>
        public static double AngleDifference(double A, double B)
        {
            double delta = NormalizeAnglePositive(B) - NormalizeAnglePositive(A);

            if (Math.Abs(delta) > Math.PI)
                delta -= Math.Sign(delta) * TwoPI;

            if (delta == -Math.PI)
                delta = Math.PI;

            return delta;
        }

        /// <summary>
        /// Minimal absolute angle.
        /// </summary>
        /// <param name="A">Angle.</param>
        /// <returns>Angle in (-PI, PI].</returns>
        public static double NormalizeAngleAbsolute(double A)
        {
            while (A <= -Math.PI)
                A += TwoPI;

            while (A > Math.PI)
                A -= TwoPI;

            return A;
        }

        /// <summary>
        /// Minimal absolute sum of two angles.
        /// </summary>
        /// <param name="A">First.</param>
        /// <param name="B">Second.</param>
        /// <returns>Angle in (-PI, PI].</returns>
        public static double AddAnglesAbsolute(double A, double B)
        {
            return NormalizeAngleAbsolute(A + B);
        }

        /// <summary>
        /// Absolute angle of a vector.
        /// </summary>
        /// <param name="x">X.</param>
        /// <param name="y">Y.</param>
        /// <returns>Angle in (-PI, PI].</returns>
        public static double VectorAngle(double x, double y)
        {
            return (x == 0 ? Math.Sign(y) * HalfPI :
                Math.Atan(y / x) + (x > 0 ? 0 :
                    (y == 0 ? Math.PI : 
                        Math.Sign(y) * Math.PI)));
        }

        /// <summary>
        /// Absolute angle of a vector pointing from A to B.
        /// </summary>
        /// <param name="A">From.</param>
        /// <param name="B">To.</param>
        /// <returns>Angle in (-PI, PI].</returns>
        public static double SegmentAngle(Point A, Point B)
        {
            return VectorAngle(B.X - A.X, B.Y - A.Y);
        }

        /// <summary>
        /// Absolute angle from segment A (pointing from A1 to A2) to segment B (pointing from B1 to B2).
        /// </summary>
        /// <param name="A1">From from.</param>
        /// <param name="A2">From to.</param>
        /// <param name="B1">To from.</param>
        /// <param name="B2">To to.</param>
        /// <returns>Angle in (-PI, PI].</returns>
        public static double AngleBetweenSegments(Point A1, Point A2, Point B1, Point B2)
        {
            return AngleDifference(SegmentAngle(A1, A2), SegmentAngle(B1, B2));
        }

        public static double AngleBisection(double A, double B)
        {
            return Mathematics.NormalizeAngleAbsolute((Mathematics.NormalizeAnglePositive(A) + Mathematics.NormalizeAnglePositive(B)) / 2);
        }

        //public static bool SegmentVisibility(Point A, Point B1, Point B2, out double angle)
        //{
        //    angle = AngleBetweenSegments(B1, B2, A, B1);
        //    return angle > 0 && angle < TwoPI;
        //}

        //public static bool IsSegmentVisible(Point A, Point B1, Point B2)
        //{
        //    double tmpAngle = AngleBetweenSegments(B1, B2, A, B1);
        //    return tmpAngle > 0 && tmpAngle < TwoPI;
        //}

        public static double Pythagor(double x, double y)
        {
            return Math.Sqrt(x * x + y * y);
        }

        public static double Distance(Point A, Point B)
        {
            return Pythagor(B.X - A.X, B.Y - A.Y);
        }
    }
}
