namespace Com.CodeGame.CodeTanks2012.DevKit.CSharpCgdk.Model
{
    public sealed class Bonus : Unit
    {
        private readonly BonusType type;

        public Bonus(long id, double width, double height, double x, double y, BonusType type)
            : base(id, width, height, x, y, 0.0D, 0.0D, 0.0D, 0.0D)
        {
            this.type = type;
        }

        public BonusType Type
        {
            get { return type; }
        }
    }
}
