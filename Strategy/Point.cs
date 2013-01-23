namespace Com.CodeGame.CodeTanks2012.DevKit.CSharpCgdk
{
    public class Point
    {
        public double X;
        public double Y;

        public Point()
        {
            X = 0;
            Y = 0;
        }

        public Point(double x, double y)
        {
            X = x;
            Y = y;
        }

        public Point(Point point)
        {
            X = point.X;
            Y = point.Y;
        }
    }
}
