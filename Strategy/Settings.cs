using System;
using System.Collections.Generic;
using Com.CodeGame.CodeTanks2012.DevKit.CSharpCgdk.Model;

namespace Com.CodeGame.CodeTanks2012.DevKit.CSharpCgdk
{
    public static class Settings
    {
        public const int BufferSize = 3;
        public const int PredictionLength = 128;
        public const double MaxSpeed = 3.9583322;
        public const double MaxAngleSpeed = 0.03060894807179769;
        public const int PredictionAngleCount = 45; // 0.5° [for each side]
        public const double PredictionAngleStep = Math.PI / PredictionAngleCount;
        public const int FieldWidth = 1280;
        public const int FieldHeight = 800;
        public const double FieldDiagonal = 1509.436981129056;
        public const int SafeMaxLength = 1600;
        public const double TickPrecision = 1;
        public const int BonusLifeTime = 1000;
        public const int BonusSize = 30;
        public const int BonusHealth = 35;
        public const int BonusRepair = 50;
        public const int BonusPremium = 3;
        public const int SafeMaxTicks = 10000;
        public const double TickApproximationEpsilon = 0.1;

        public const double RegularLimit = 0.005;
        public const double PremiumLimit = 0.05;
    }

    public interface TankProperties
    {
        double Length { get; }
        double Width { get; }
        double CentralAngle { get; }
        double HalfDiagonal { get; }
        double TurrentLength { get; }
        double Mass { get; }
        double MaxSpeed { get; }
        double ReverseRatio { get; }
        double MaxAngularSpeed { get; }
        double MaxTurretAngularSpeed { get; }
        double MaxTurretAngle { get; }
        int MaxHealth { get; }
        int MaxHullDurability { get; }
        Dictionary<ArmorType, int> ArmorProperties { get; }
        int ReloadTime { get; }
    }

    public class MediumTank : TankProperties
    {
        public double Length { get { return 90; } }
        public double Width { get { return 60; } }
        public double CentralAngle { get; private set; }
        public double HalfDiagonal { get; private set; }
        public double TurrentLength { get { return 67.5; } }
        public double Mass { get { return 10; } }
        public double MaxSpeed { get { return 3.9583322; } }
        public double ReverseRatio { get { return 0.75; } }
        public double MaxAngularSpeed { get { return 0.03060894807179769; } }
        public double MaxTurretAngularSpeed { get { return 0.017453292519943; } }
        public double MaxTurretAngle { get { return Math.PI; } }
        public int MaxHealth { get { return 100; } }
        public int MaxHullDurability { get { return 200; } }
        public Dictionary<ArmorType, int> ArmorProperties { get; private set; }
        public int ReloadTime { get { return 150; } }

        public MediumTank()
        {
            CentralAngle = Math.Atan(Width / Length);
            HalfDiagonal = Mathematics.Pythagor(Length, Width) / 2;
            ArmorProperties = new Dictionary<ArmorType, int>()
            {
                { ArmorType.Front, 175 },
                { ArmorType.Side, 150 },
                { ArmorType.Rear, 100 }
            };
        }
    }

    public class HeavyTank : TankProperties
    {
        public double Length { get { return 105; } }
        public double Width { get { return 75; } }
        public double CentralAngle { get; private set; }
        public double HalfDiagonal { get; private set; }
        public double TurrentLength { get { return 82.5; } }
        public double Mass { get { return 20; } }
        public double MaxSpeed { get { return 1.9791661; } }
        public double ReverseRatio { get { return 0.5; } }
        public double MaxAngularSpeed { get { return 0.010202982690599; } }
        public double MaxTurretAngularSpeed { get { return 0.0087266462599715; } }
        public double MaxTurretAngle { get { return Math.PI; } }
        public int MaxHealth { get { return 100; } }
        public int MaxHullDurability { get { return 250; } }
        public Dictionary<ArmorType, int> ArmorProperties { get; private set; }
        public int ReloadTime { get { return 150; } }

        public HeavyTank()
        {
            CentralAngle = Math.Atan(Width / Length);
            HalfDiagonal = Mathematics.Pythagor(Length, Width) / 2;
            ArmorProperties = new Dictionary<ArmorType, int>()
            {
                { ArmorType.Front, 200 },
                { ArmorType.Side, 175 },
                { ArmorType.Rear, 100 }
            };
        }
    }

    public class DestroyerTank : TankProperties
    {
        public double Length { get { return 112.5; } }
        public double Width { get { return 67.5; } }
        public double CentralAngle { get; private set; }
        public double HalfDiagonal { get; private set; }
        public double TurrentLength { get { return 97.5; } }
        public double Mass { get { return 15; } }
        public double MaxSpeed { get { return 1.759258755555556; } }
        public double ReverseRatio { get { return 0.35; } }
        public double MaxAngularSpeed { get { return 0.0071420878834195; } }
        public double MaxTurretAngularSpeed { get { return 0.026179938779915; } }
        public double MaxTurretAngle { get { return 0.26179938779915; } }
        public int MaxHealth { get { return 100; } }
        public int MaxHullDurability { get { return 250; } }
        public Dictionary<ArmorType, int> ArmorProperties { get; private set; }
        public int ReloadTime { get { return 135; } }

        public DestroyerTank()
        {
            CentralAngle = Math.Atan(Width / Length);
            HalfDiagonal = Mathematics.Pythagor(Length, Width) / 2;
            ArmorProperties = new Dictionary<ArmorType, int>()
            {
                { ArmorType.Front, 200 },
                { ArmorType.Side, 125 },
                { ArmorType.Rear, 100 }
            };
        }
    }

    public enum ArmorType
    {
        Front,
        Side,
        Rear
    }

    public interface ShellProperties
    {
        ShellType Type { get; }
        double Length { get; }
        double Width { get; }
        double Mass { get; }
        double InitialSpeed { get; }
        double SpeedPowerRatio { get; }
        double Damage { get; }
        double ArmorPenetration { get; }
        bool ArmorPenetrationDecline { get; }
        bool RicochetPossible { get; }
        double RicochetAngle { get; }
        int LifeTime { get; }
    }

    public class RegularShell : ShellProperties
    {
        public ShellType Type { get { return ShellType.Regular; } }
        public double Length { get { return 22.5; } }
        public double Width { get { return 7.5; } }
        public double Mass { get { return 1; } }
        public double InitialSpeed { get { return 16.6666656903113; } }
        public double SpeedPowerRatio { get { return -0.00501254197740607; } }
        public double Damage { get { return 20; } }
        public double ArmorPenetration { get { return 215; } }
        public bool ArmorPenetrationDecline { get { return true; } }
        public bool RicochetPossible { get { return true; } }
        public double RicochetAngle { get { return 1.047197551196598; } }
        public int LifeTime { get { return 107; } }
    }

    public class PremiumShell : ShellProperties
    {
        public ShellType Type { get { return ShellType.Premium; } }
        public double Length { get { return 22.5; } }
        public double Width { get { return 7.5; } }
        public double Mass { get { return 0.5; } }
        public double InitialSpeed { get { return 13.3333300297773; } }
        public double SpeedPowerRatio { get { return -0.0100503347333318; } }
        public double Damage { get { return 35; } }
        public double ArmorPenetration { get { return 250; } }
        public bool ArmorPenetrationDecline { get { return false; } }
        public bool RicochetPossible { get { return false; } }
        public double RicochetAngle { get { return 0; } }
        public int LifeTime { get { return 207; } }
    }
}
