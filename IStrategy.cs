using Com.CodeGame.CodeTanks2012.DevKit.CSharpCgdk.Model;

namespace Com.CodeGame.CodeTanks2012.DevKit.CSharpCgdk
{
    public interface IStrategy
    {
        void Move(Tank self, World world, Move move);

        TankType SelectTank(int tankIndex, int teamSize);
    }
}
