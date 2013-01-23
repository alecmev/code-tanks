namespace Com.CodeGame.CodeTanks2012.DevKit.CSharpCgdk.Model
{
    public sealed class Obstacle : Unit
    {
        public Obstacle(long id, double width, double height, double x, double y)
            : base(id, width, height, x, y, 0.0D, 0.0D, 0.0D, 0.0D) { }
    }
}
