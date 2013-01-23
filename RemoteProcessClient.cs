using System;
using System.IO;
using System.Net.Sockets;
using System.Text;
using Com.CodeGame.CodeTanks2012.DevKit.CSharpCgdk.Model;

namespace Com.CodeGame.CodeTanks2012.DevKit.CSharpCgdk
{
    public sealed class RemoteProcessClient
    {
        private const int BufferSizeBytes = 1 << 20;

        private readonly TcpClient client;
        private readonly BinaryReader reader;
        private readonly BinaryWriter writer;

        public RemoteProcessClient(string host, int port)
        {
            client = new TcpClient(host, port);
            client.SendBufferSize = BufferSizeBytes;
            client.ReceiveBufferSize = BufferSizeBytes;

            reader = new BinaryReader(client.GetStream());
            writer = new BinaryWriter(client.GetStream());
        }

        public void WriteToken(string token)
        {
            WriteEnum((sbyte?)MessageType.AuthenticationToken);
            WriteString(token);
            writer.Flush();
        }

        public int ReadTeamSize()
        {
            EnsureMessageType((MessageType)ReadEnum(), MessageType.TeamSize);
            return ReadInt();
        }

        public void WriteSelectedTanks(TankType[] tankTypes)
        {
            WriteEnum((sbyte?)MessageType.TankTypes);

            if (tankTypes == null)
            {
                WriteInt(-1);
            }
            else
            {
                int typeCount = tankTypes.Length;
                WriteInt(typeCount);

                for (int typeIndex = 0; typeIndex < typeCount; ++typeIndex)
                {
                    WriteEnum((sbyte?)tankTypes[typeIndex]);
                }
            }

            writer.Flush();
        }

        public PlayerContext ReadPlayerContext()
        {
            MessageType messageType = (MessageType)ReadEnum();
            if (messageType == MessageType.GameOver)
            {
                return null;
            }

            EnsureMessageType(messageType, MessageType.PlayerContext);
            return ReadBoolean() ? new PlayerContext(ReadTanks(), ReadWorld()) : null;
        }

        public void WriteMoves(Move[] moves)
        {
            WriteEnum((sbyte?)MessageType.Moves);

            if (moves == null)
            {
                WriteInt(-1);
            }
            else
            {
                int moveCount = moves.Length;
                WriteInt(moveCount);

                for (int moveIndex = 0; moveIndex < moveCount; ++moveIndex)
                {
                    Move move = moves[moveIndex];

                    if (move == null)
                    {
                        WriteBoolean(false);
                    }
                    else
                    {
                        WriteBoolean(true);

                        WriteDouble(move.LeftTrackPower);
                        WriteDouble(move.RightTrackPower);
                        WriteDouble(move.TurretTurn);
                        WriteEnum((sbyte?)move.FireType);
                    }
                }
            }

            writer.Flush();
        }

        public void Close()
        {
            client.Close();
        }

        private World ReadWorld()
        {
            if (!ReadBoolean())
            {
                return null;
            }

            return new World(ReadInt(), ReadDouble(), ReadDouble(), ReadPlayers(), ReadObstacles(), ReadTanks(), ReadShells(), ReadBonuses());
        }

        private Player[] ReadPlayers()
        {
            int playerCount = ReadInt();
            if (playerCount < 0)
            {
                return null;
            }

            Player[] players = new Player[playerCount];

            for (int playerIndex = 0; playerIndex < playerCount; ++playerIndex)
            {
                if (ReadBoolean())
                {
                    players[playerIndex] = new Player(ReadString(), ReadInt(), ReadBoolean());
                }
            }

            return players;
        }

        private Obstacle[] ReadObstacles()
        {
            int obstacleCount = ReadInt();
            if (obstacleCount < 0)
            {
                return null;
            }

            Obstacle[] obstacles = new Obstacle[obstacleCount];

            for (int obstacleIndex = 0; obstacleIndex < obstacleCount; ++obstacleIndex)
            {
                if (ReadBoolean())
                {
                    obstacles[obstacleIndex] = new Obstacle(ReadLong(), ReadDouble(), ReadDouble(), ReadDouble(), ReadDouble());
                }
            }

            return obstacles;
        }

        private Tank[] ReadTanks()
        {
            int tankCount = ReadInt();
            if (tankCount < 0)
            {
                return null;
            }

            Tank[] tanks = new Tank[tankCount];

            for (int tankIndex = 0; tankIndex < tankCount; ++tankIndex)
            {
                if (ReadBoolean())
                {
                    tanks[tankIndex] = new Tank(
                            ReadLong(), ReadString(), ReadInt(), ReadDouble(), ReadDouble(),
                            ReadDouble(), ReadDouble(), ReadDouble(), ReadDouble(), ReadDouble(),
                            ReadInt(), ReadInt(), ReadInt(), ReadInt(), ReadInt(),
                            ReadBoolean(), (TankType)ReadEnum()
                    );
                }
            }

            return tanks;
        }

        private Shell[] ReadShells()
        {
            int shellCount = ReadInt();
            if (shellCount < 0)
            {
                return null;
            }

            Shell[] shells = new Shell[shellCount];

            for (int shellIndex = 0; shellIndex < shellCount; ++shellIndex)
            {
                if (ReadBoolean())
                {
                    shells[shellIndex] = new Shell(
                        ReadLong(), ReadString(), ReadDouble(), ReadDouble(), ReadDouble(), ReadDouble(),
                        ReadDouble(), ReadDouble(), ReadDouble(), ReadDouble(), (ShellType)ReadEnum()
                    );
                }
            }

            return shells;
        }

        private Bonus[] ReadBonuses()
        {
            int bonusCount = ReadInt();
            if (bonusCount < 0)
            {
                return null;
            }

            Bonus[] bonuses = new Bonus[bonusCount];

            for (int bonusIndex = 0; bonusIndex < bonusCount; ++bonusIndex)
            {
                if (ReadBoolean())
                {
                    bonuses[bonusIndex] = new Bonus(ReadLong(), ReadDouble(), ReadDouble(), ReadDouble(), ReadDouble(), (BonusType)ReadEnum());
                }
            }

            return bonuses;
        }

        private static void EnsureMessageType(MessageType actualType, MessageType expectedType)
        {
            if (actualType != expectedType)
            {
                throw new ArgumentException(string.Format("Received wrong message [actual={0}, expected={1}].", actualType, expectedType));
            }
        }

        private sbyte? ReadEnum()
        {
            sbyte value = reader.ReadSByte();
            return value < 0 ? null : (sbyte?)value;
        }

        private void WriteEnum(sbyte? value)
        {
            writer.Write((sbyte)(value == null ? -1 : value));
        }

        private string ReadString()
        {
            int length = ReadInt();
            if (length == -1)
            {
                return null;
            }

            return Encoding.UTF8.GetString(ReadBytes(length));
        }

        private void WriteString(string value)
        {
            if (value == null)
            {
                WriteInt(-1);
                return;
            }

            byte[] bytes = Encoding.UTF8.GetBytes(value);

            WriteInt(bytes.Length);
            WriteBytes(bytes);
        }

        private bool ReadBoolean()
        {
            return reader.ReadSByte() == 1;
        }

        private void WriteBoolean(bool value)
        {
            writer.Write((sbyte)(value ? 1 : 0));
        }

        private int ReadInt()
        {
            return reader.ReadInt32();
        }

        private void WriteInt(int value)
        {
            writer.Write(value);
        }

        private long ReadLong()
        {
            return reader.ReadInt64();
        }

        private void WriteLong(long value)
        {
            writer.Write(value);
        }

        private double ReadDouble()
        {
            return reader.ReadDouble();
        }

        private void WriteDouble(double value)
        {
            writer.Write(value);
        }

        private byte[] ReadBytes(int byteCount)
        {
            return reader.ReadBytes(byteCount);
        }

        private void WriteBytes(byte[] bytes)
        {
            writer.Write(bytes);
        }

        private enum MessageType
        {
            Unknown,
            GameOver,
            AuthenticationToken,
            TeamSize,
            TankTypes,
            PlayerContext,
            Moves
        }
    }
}
