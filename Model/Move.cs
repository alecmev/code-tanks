namespace Com.CodeGame.CodeTanks2012.DevKit.CSharpCgdk.Model
{
    public sealed class Move
    {
        /// <summary>
        /// Should be from <code>-1.0D</code> to <code>1.0D</code>.
        /// </summary>
        private double leftTrackPower;

        /// <summary>
        /// Should be from <code>-1.0D</code> to <code>1.0D</code>.
        /// </summary>
        private double rightTrackPower;

        private double turretTurn;

        private FireType? fireType;

        public double LeftTrackPower
        {
            get { return leftTrackPower; }
            set { leftTrackPower = value; }
        }

        public double RightTrackPower
        {
            get { return rightTrackPower; }
            set { rightTrackPower = value; }
        }

        public double TurretTurn
        {
            get { return turretTurn; }
            set { turretTurn = value; }
        }

        public FireType? FireType
        {
            get { return fireType; }
            set { fireType = value; }
        }
    }
}
