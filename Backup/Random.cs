//public const double EI = 1.0 / Math.E;

//public static double LambertW(double x)
//{
//    double w;

//    if (x < EI)
//        w = LambertSeriesZero(x);
//    else if (x > Math.E)
//        w = LambertSeriesLarge(x);
//    else
//        w = 0.5;

//    return LambertHalley(x, w);
//}

//private static double LambertSeriesZero(double x)
//{
//    return x - x * x + (3.0 / 2.0) * x * x * x - (8.0 / 3.0) * x * x * x * x;
//}

//private static double LambertSeriesLarge(double x)
//{
//    double L1 = Math.Log(x);
//    double L2 = Math.Log(L1);
//    return L1 - L2 + L2 / L1 + L2 * (L2 - 2.0) / L1 / L1 / 2.0;
//}

//private static double LambertHalley(double x, double w0)
//{
//    for (int i = 0; i < 256; i++)
//    {
//        double e = Math.Exp(w0);
//        double f = e * w0 - x;
//        double dw = f / ((w0 + 1.0) * e - ((w0 + 2.0) / (w0 + 1.0)) * f / 2.0);
//        double w1 = w0 - dw;

//        if (w1 == w0)
//            return (w1);

//        w0 = w1;
//    }

//    return w0; // EXCEPTION
//}

//tmpTicks = Mathematics.LambertW(tmpProperties.SpeedPowerRatio * tmpProperties.SpeedPowerRatio * tmpDistance / tmpProperties.InitialSpeed) / tmpProperties.SpeedPowerRatio; // Analytical

//private List<long> _existingShells = new List<long>();

//List<long> tmpExistingShells = new List<long>();

//foreach (Shell tmpShell in world.Shells)
//{
//    tmpExistingShells.Add(tmpShell.Id);

//    if (!_tankDynamics.ContainsKey(tmpShell.Id))
//        _tankDynamics.Add(tmpShell.Id, new TankDynamics());
//    else
//        _existingShells.Remove(tmpShell.Id);
//}

//foreach (long tmpExpiredShell in _existingShells)
//    _tankDynamics.Remove(tmpExpiredShell);

//_existingShells = tmpExistingShells;

//double tmpAngleToTarget = self.GetAngleTo(_targetX, _targetY);

//if (!_record)
//{
//    if (!_targetSet)
//    {
//        if (self.GetDistanceTo(0, 0) > 90)
//        {
//            _targetX = 0;
//            _targetY = 0;
//            _targetSet = true;
//        }
//        else
//        {
//            _targetX = 1280;
//            _targetY = 800;

//            _fileStream = new FileStream("data.txt", FileMode.Create);
//            _streamWriter = new StreamWriter(_fileStream);
//            _record = true;
//        }
//    }
//    else
//    {
//        if (self.GetDistanceTo(_targetX, _targetY) < 91)
//        {
//            _targetSet = false;
//            Move(self, world, move);
//            return;
//        }

//        if (Math.Abs(tmpAngleToTarget) > 0.1)
//        {
//            if (Math.Sign(tmpAngleToTarget) > 0)
//            {
//                move.LeftTrackPower = 0.3525;
//                move.RightTrackPower = -0.5;
//            }
//            else
//            {
//                move.LeftTrackPower = -0.5;
//                move.RightTrackPower = 0.3525;
//            }
//        }
//        else
//        {
//            move.LeftTrackPower = 1.0;
//            move.RightTrackPower = 1.0;

//            if (Math.Sqrt(self.SpeedX * self.SpeedX + self.SpeedY * self.SpeedY) >= 3.95 && _targetX == 1280)
//                _record = true;
//        }
//    }
//}
//else if (!_targetSet && Math.Abs(tmpAngleToTarget) > 0.0001)
//{
//    if (Math.Sign(tmpAngleToTarget) > 0)
//    {
//        move.LeftTrackPower = 0.3525;
//        move.RightTrackPower = -0.5;
//    }
//    else
//    {
//        move.LeftTrackPower = -0.5;
//        move.RightTrackPower = 0.3525;
//    }
//}
//else if (self.X < 1200)
//{
//    move.LeftTrackPower = 1.0;
//    move.RightTrackPower = 1.0;
//    _targetSet = true;
//    _streamWriter.WriteLine(Math.Sqrt(self.SpeedX * self.SpeedX + self.SpeedY * self.SpeedY));
//}
//else if (_fileStream != null)
//{
//    _streamWriter.Close();
//    _fileStream.Close();
//    _fileStream = null;
//}

//private FileStream _fileStream;
//private StreamWriter _streamWriter;

//private double _targetX;
//private double _targetY;
//private bool _targetSet = false;
//private bool _record = false;

//if (!_record)
//{
//    if (!_targetSet)
//    {
//        if (self.GetDistanceTo(0, 0) > 90)
//        {
//            _targetX = 0;
//            _targetY = 0;
//            _targetSet = true;
//        }
//        else
//        {
//            _targetX = 1280;
//            _targetY = 800;
//            _targetSet = true;

//            _fileStream = new FileStream("data.txt", FileMode.Create);
//            _streamWriter = new StreamWriter(_fileStream);
//        }
//    }
//    else
//    {
//        if (self.GetDistanceTo(_targetX, _targetY) < 91)
//        {
//            _targetSet = false;
//            Move(self, world, move);
//            return;
//        }

//        double tmpAngleToTarget = self.GetAngleTo(_targetX, _targetY);

//        if (Math.Abs(tmpAngleToTarget) > 0.1)
//        {
//            if (Math.Sign(tmpAngleToTarget) > 0)
//            {
//                move.LeftTrackPower = 0.3525;
//                move.RightTrackPower = -0.5;
//            }
//            else
//            {
//                move.LeftTrackPower = -0.5;
//                move.RightTrackPower = 0.3525;
//            }
//        }
//        else
//        {
//            move.LeftTrackPower = 1.0;
//            move.RightTrackPower = 1.0;

//            if (Math.Sqrt(self.SpeedX * self.SpeedX + self.SpeedY * self.SpeedY) >= 3.95 && _targetX == 1280)
//                _record = true;
//        }
//    }
//}
//else if (self.SpeedX + self.SpeedY > 0.001)
//{
//    _streamWriter.WriteLine(Math.Sqrt(self.SpeedX * self.SpeedX + self.SpeedY * self.SpeedY));
//}
//else if (_fileStream != null)
//{
//    _streamWriter.Close();
//    _fileStream.Close();
//    _fileStream = null;
//}

//private double _prevSpeed = -100;
//private double _prevDelta = -100;
//private FileStream _fileStream;
//private StreamWriter _streamWriter;

//private double _targetX;
//private double _targetY;
//private bool _targetSet = false;

//private bool _shellFired = false;

//private long _targetID = -1;

//if (world.Tanks.Length > 0)
//{
//    if (_targetID < 0)
//    {
//        foreach (Tank tmpTank in world.Tanks)
//            if (tmpTank.CrewHealth > 0 && tmpTank.HullDurability > 0 && tmpTank.Id != self.Id)
//            {
//                _targetID = tmpTank.Id;
//                Move(self, world, move);
//                return;
//            }
//    }
//    else
//    {
//        Tank tmpTargetTank = null;

//        foreach (Tank tmpTank in world.Tanks)
//            if (tmpTank.Id == _targetID)
//            {
//                tmpTargetTank = tmpTank;
//                break;
//            }

//        if (tmpTargetTank.CrewHealth == 0 || tmpTargetTank.HullDurability == 0)
//        {
//            _targetID = -1;
//            Move(self, world, move);
//            return;
//        }

//        double tmpTargetAngle = self.GetTurretAngleTo(tmpTargetTank);

//        if (Math.Abs(tmpTargetAngle) > 0.001)
//            move.TurretTurn = tmpTargetAngle;
//        else
//            move.FireType = FireType.Regular;
//    }
//}

//if (!_shellFired)
//{
//    if (!_targetSet)
//    {
//        if (self.PremiumShellCount == 0)
//        {
//            foreach (Bonus tmpBonus in world.Bonuses)
//            {
//                if (tmpBonus.Type == BonusType.AmmoCrate)
//                {
//                    _targetX = tmpBonus.X;
//                    _targetY = tmpBonus.Y;
//                    _targetSet = true;
//                }
//            }
//        }
//        else if (self.GetDistanceTo(0, 0) > 90)
//        {
//            _targetX = 0;
//            _targetY = 0;
//            _targetSet = true;
//        }
//        else
//        {
//            double tmpAngleTo = self.GetTurretAngleTo(1280, 800);

//            if (Math.Abs(tmpAngleTo) > 0.01)
//                move.TurretTurn = tmpAngleTo;
//            else
//            {
//                move.FireType = FireType.Premium;
//                _shellFired = true;
//                _fileStream = new FileStream("data.txt", FileMode.Create);
//                _streamWriter = new StreamWriter(_fileStream);
//            }
//        }
//    }
//    else
//    {
//        if (self.GetDistanceTo(_targetX, _targetY) < 91)
//        {
//            _targetSet = false;
//            Move(self, world, move);
//            return;
//        }

//        double tmpAngleToTarget = self.GetAngleTo(_targetX, _targetY);

//        if (Math.Abs(tmpAngleToTarget) > 0.05)
//        {
//            if (Math.Sign(tmpAngleToTarget) > 0)
//            {
//                move.LeftTrackPower = 0.75;
//                move.RightTrackPower = -1;
//            }
//            else
//            {
//                move.LeftTrackPower = -1;
//                move.RightTrackPower = 0.75;
//            }
//        }
//        else
//        {
//            move.LeftTrackPower = 1.0;
//            move.RightTrackPower = 1.0;
//        }
//    }
//}
//else if (world.Shells.Length > 0)
//{
//    _streamWriter.WriteLine(Math.Sqrt(world.Shells[0].SpeedX * world.Shells[0].SpeedX + world.Shells[0].SpeedY * world.Shells[0].SpeedY));
//}
//else if (_fileStream != null)
//{
//    _streamWriter.Close();
//    _fileStream.Close();
//    _fileStream = null;
//}

//if (world.Tick < 20)
//{
//    move.LeftTrackPower = -1.0;
//    move.RightTrackPower = 1.0;
//}
//else if (world.Tick < 350)
//{
//    move.LeftTrackPower = -0.5;
//    move.RightTrackPower = -1.0;
//}
//else if (world.Tick < 400)
//{
//    move.TurretTurn = self.GetTurretAngleTo(1280, 800);
//}
//else
//{
//    if (world.Tick == 400)
//    {
//        move.FireType = FireType.Premium;

//        _fileStream = new FileStream("data.txt", FileMode.Create);
//        _streamWriter = new StreamWriter(_fileStream);
//    }
//    else if (world.Shells.Length > 0)
//    {
//        double tmpSpeed = Math.Sqrt(world.Shells[0].SpeedX * world.Shells[0].SpeedX + world.Shells[0].SpeedY * world.Shells[0].SpeedY);
//        double tmpDelta = _prevSpeed - tmpSpeed;

//        //if (world.Tick > 300)
//        //{
//            _streamWriter.WriteLine(tmpSpeed);
//            //Console.Out.WriteLine(tmpDelta + " ( " + (tmpDelta - _prevDelta) + " )");
//        //}

//        _prevSpeed = tmpSpeed;
//        _prevDelta = tmpDelta;
//    }
//    else if (_fileStream != null)
//    {
//        _streamWriter.Close();
//        _fileStream.Close();
//        _fileStream = null;
//    }

//    //move.LeftTrackPower = -1.0;
//    //move.RightTrackPower = -1.0;

//    //double tmpSpeed = Math.Sqrt(self.SpeedX * self.SpeedX + self.SpeedY * self.SpeedY);

//    //if (tmpSpeed - _prevSpeed < 0.00001)
//    //{
//    //    Console.Out.WriteLine(tmpSpeed);
//    //    Console.In.ReadLine();
//    //}

//    //_prevSpeed = tmpSpeed;
//}

//Console.In.ReadLine();

//Tank tmpGuy = null;

//foreach (Tank tmpTank in world.Tanks)
//    if (tmpTank.PlayerName == "QuickStartGuy")
//    {
//        tmpGuy = tmpTank;
//        break;
//    }

//double tmpAngle = self.GetAngleTo(tmpGuy) - self.TurretRelativeAngle;
//move.TurretTurn = tmpAngle;

//if (Math.Abs(tmpAngle) < 0.01)
//    move.FireType = FireType.PremiumPreferred;
//else if (self.GetAngleTo(tmpGuy) > 0)
//{
//    move.LeftTrackPower = self.EngineRearPowerFactor;
//    move.RightTrackPower = -1.0;
//}
//else
//{
//    move.LeftTrackPower = -1.0;
//    move.RightTrackPower = self.EngineRearPowerFactor;
//}