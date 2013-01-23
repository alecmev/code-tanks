namespace Com.CodeGame.CodeTanks2012.DevKit.CSharpCgdk
{
    public class Line
    {
        public Point A;
        public Point B;

        public Line()
        {
            A = new Point(0, 0);
            B = new Point(Settings.FieldWidth, Settings.FieldHeight);
        }

        public Line(double ax, double ay, double bx, double by)
        {
            A = new Point(ax, ay);
            B = new Point(bx, by);
        }

        public Line(Point a, Point b)
        {
            A = new Point(a);
            B = new Point(b);
        }

        public Line(Line line)
        {
            A = new Point(line.A);
            B = new Point(line.B);
        }
    }
}
