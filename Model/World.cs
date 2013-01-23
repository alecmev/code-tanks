using System;

namespace Com.CodeGame.CodeTanks2012.DevKit.CSharpCgdk.Model
{
    public sealed class World
    {
        private readonly int tick;
        private readonly double width;
        private readonly double height;
        private readonly Player[] players;
        private readonly Obstacle[] obstacles;
        private readonly Tank[] tanks;
        private readonly Shell[] shells;
        private readonly Bonus[] bonuses;

        public World(int tick, double width, double height, Player[] players, Obstacle[] obstacles, Tank[] tanks, Shell[] shells, Bonus[] bonuses)
        {
            this.tick = tick;
            this.width = width;
            this.height = height;

            this.players = new Player[players.Length];
            Array.Copy(players, this.players, players.Length);

            this.obstacles = new Obstacle[obstacles.Length];
            Array.Copy(obstacles, this.obstacles, obstacles.Length);

            this.tanks = new Tank[tanks.Length];
            Array.Copy(tanks, this.tanks, tanks.Length);

            this.shells = new Shell[shells.Length];
            Array.Copy(shells, this.shells, shells.Length);

            this.bonuses = new Bonus[bonuses.Length];
            Array.Copy(bonuses, this.bonuses, bonuses.Length);
        }

        public int Tick
        {
            get { return tick; }
        }

        public double Width
        {
            get { return width; }
        }

        public double Height
        {
            get { return height; }
        }

        public Player[] Players
        {
            get
            {
                Player[] players = new Player[this.players.Length];
                Array.Copy(this.players, players, this.players.Length);
                return players;
            }
        }

        public Obstacle[] Obstacles
        {
            get
            {
                Obstacle[] obstacles = new Obstacle[this.obstacles.Length];
                Array.Copy(this.obstacles, obstacles, this.obstacles.Length);
                return obstacles;
            }
        }

        public Tank[] Tanks
        {
            get
            {
                Tank[] tanks = new Tank[this.tanks.Length];
                Array.Copy(this.tanks, tanks, this.tanks.Length);
                return tanks;
            }
        }

        public Shell[] Shells
        {
            get
            {
                Shell[] shells = new Shell[this.shells.Length];
                Array.Copy(this.shells, shells, this.shells.Length);
                return shells;
            }
        }

        public Bonus[] Bonuses
        {
            get
            {
                Bonus[] bonuses = new Bonus[this.bonuses.Length];
                Array.Copy(this.bonuses, bonuses, this.bonuses.Length);
                return bonuses;
            }
        }
    }
}
