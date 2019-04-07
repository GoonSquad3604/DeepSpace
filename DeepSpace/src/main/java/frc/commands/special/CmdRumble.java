package frc.commands.special;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import frc.commands.AutonCommand;

public class CmdRumble implements AutonCommand
{

    private Timer time;
    private double runTime;
    private double value;
    private XboxController controller;

    public CmdRumble(XboxController iController, double iTime, double iValue)
    {
        controller = iController;
        runTime = iTime;
        value = iValue;
        time = new Timer();
    }

    @Override
    public boolean isFinished() {
        return time.get() >= runTime;
    }

    @Override
    public void runTask() {
        controller.setRumble(RumbleType.kLeftRumble, value);
        controller.setRumble(RumbleType.kRightRumble, value);
    }

    @Override
    public double getStatus() {
        return 0;
    }

    @Override
    public void init() {
        time.start();
        time.reset();
    }

    @Override
    public void end() {
        controller.setRumble(RumbleType.kLeftRumble, 0);
        controller.setRumble(RumbleType.kRightRumble, 0);
    }


}