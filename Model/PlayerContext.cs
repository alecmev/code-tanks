using System;

namespace Com.CodeGame.CodeTanks2012.DevKit.CSharpCgdk.Model
{
    public sealed class PlayerContext
    {
        private readonly Tank[] tanks;
        private readonly World world;

        public PlayerContext(Tank[] tanks, World world)
        {
            this.tanks = new Tank[tanks.Length];
            Array.Copy(tanks, this.tanks, tanks.Length);

            this.world = world;
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

        public World World
        {
            get { return world; }
        }
    }
}
