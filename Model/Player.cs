namespace Com.CodeGame.CodeTanks2012.DevKit.CSharpCgdk.Model
{
    public sealed class Player
    {
        private readonly string name;
        private readonly int score;
        private readonly bool isStrategyCrashed;

        public Player(string name, int score, bool isStrategyCrashed)
        {
            this.name = name;
            this.score = score;
            this.isStrategyCrashed = isStrategyCrashed;
        }

        public string Name
        {
            get { return name; }
        }

        public int Score
        {
            get { return score; }
        }

        public bool IsStrategyCrashed
        {
            get { return isStrategyCrashed; }
        }
    }
}
