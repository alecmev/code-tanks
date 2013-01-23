namespace Com.CodeGame.CodeTanks2012.DevKit.CSharpCgdk.Model
{
    public sealed class Shell : Unit
    {
        private readonly string playerName;
        private readonly ShellType type;

        public Shell(long id, string playerName, double width, double height, double x, double y,
            double speedX, double speedY, double angle, double angularSpeed, ShellType type)
            : base(id, width, height, x, y, speedX, speedY, angle, angularSpeed)
        {
            this.playerName = playerName;
            this.type = type;
        }

        public string PlayerName
        {
            get { return playerName; }
        }

        public ShellType Type
        {
            get { return type; }
        }
    }
}
