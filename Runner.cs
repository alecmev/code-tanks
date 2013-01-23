using System;
using Com.CodeGame.CodeTanks2012.DevKit.CSharpCgdk.Model;

namespace Com.CodeGame.CodeTanks2012.DevKit.CSharpCgdk
{
    public sealed class Runner
    {
        private readonly RemoteProcessClient remoteProcessClient;
        private readonly string token;

        public static void Main(string[] args)
        {
            System.Diagnostics.Process.Start("javaw", "-cp \".;*;%~dp0/*\" -jar \"..\\..\\#local-runner\\local-runner.jar\"");

            if (args.Length == 3)
            {
                new Runner(args).run();
            }
            else
            {
                new Runner(new string[] { "localhost", "31000", "0000000000000000" }).run();
            }
        }

        private Runner(string[] args)
        {
            remoteProcessClient = new RemoteProcessClient(args[0], int.Parse(args[1]));
            token = args[2];
        }

        public void run()
        {
            try
            {
                remoteProcessClient.WriteToken(token);
                int teamSize = remoteProcessClient.ReadTeamSize();

                IStrategy[] strategies = new IStrategy[teamSize];
                TankType[] tankTypes = new TankType[teamSize];

                for (int strategyIndex = 0; strategyIndex < teamSize; ++strategyIndex)
                {
                    IStrategy strategy = new MyStrategy();
                    strategies[strategyIndex] = strategy;
                    tankTypes[strategyIndex] = strategy.SelectTank(strategyIndex, teamSize);
                }

                remoteProcessClient.WriteSelectedTanks(tankTypes);

                PlayerContext playerContext;

                while ((playerContext = remoteProcessClient.ReadPlayerContext()) != null)
                {
                    Tank[] playerTanks = playerContext.Tanks;
                    if (playerTanks.Length != teamSize)
                    {
                        break;
                    }

                    Move[] moves = new Move[teamSize];

                    for (int strategyIndex = 0; strategyIndex < teamSize; ++strategyIndex)
                    {
                        Move move = new Move();
                        moves[strategyIndex] = move;
                        strategies[strategyIndex].Move(playerTanks[strategyIndex], playerContext.World, move);
                    }

                    remoteProcessClient.WriteMoves(moves);
                }
            }
            finally
            {
                remoteProcessClient.Close();
            }
        }
    }
}
