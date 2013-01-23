using Com.CodeGame.CodeTanks2012.DevKit.CSharpCgdk.Model;

namespace Com.CodeGame.CodeTanks2012.DevKit.CSharpCgdk
{
    //public class BonusPlus
    //{
    //    public long Id { get; private set; }
    //    public Bonus Bonus { get { return MyStrategy.Bonuses[Id]; } }
    //    public int DeathTick { get; private set; }
    //    public Surface[] Surfaces { get; private set; }

    //    public BonusPlus(long id, int deathTick)
    //    {
    //        Id = id;
    //        DeathTick = deathTick;
    //        Surfaces = new Surface[4];

    //        double x1 = Bonus.X - Settings.BonusSize / 2d;
    //        double x2 = Bonus.X + Settings.BonusSize / 2d;
    //        double y1 = Bonus.Y - Settings.BonusSize / 2d;
    //        double y2 = Bonus.Y + Settings.BonusSize / 2d;

    //        Surfaces[0] = new Surface(new Line(x1, y1, x2, y1), -1);
    //        Surfaces[1] = new Surface(new Line(x2, y1, x2, y2), -1);
    //        Surfaces[2] = new Surface(new Line(x2, y2, x1, y2), -1);
    //        Surfaces[3] = new Surface(new Line(x1, y2, x1, y1), -1);
    //    }

    //    public double Collision(Point A, double angle, ShellType shellType)
    //    {
    //        Collision tmpCollision;

    //        foreach (Surface tmpSurface in Surfaces)
    //            if ((tmpCollision = tmpSurface.Collision(A, angle, shellType)) != null && tmpCollision.Ticks + MyStrategy.World.Tick < DeathTick)
    //                return tmpCollision.Ticks;

    //        return -1;
    //    }
    //}
}
