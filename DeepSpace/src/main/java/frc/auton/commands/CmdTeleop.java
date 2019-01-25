package frc.auton.commands;

import edu.wpi.first.wpilibj.XboxController;
import frc.subsystem.DriveTrain;

public class CmdTeleop implements AutonCommand{

    //Runs when there is no command. Does Teleoperated Stuffs.
    private DriveTrain drive;
    private XboxController driveStick;
    private XboxController operateStick;
    public CmdTeleop(DriveTrain drive, XboxController driveStick, XboxController operateStick)
    {
        this.driveStick = driveStick;
        this.operateStick = operateStick;
        this.drive = drive;
    }
    @Override
    public boolean isFinished() 
    {
        return false;
    }

    @Override
    public void runTask() 
    {
        drive.arcadeDrive(-driveStick.getRawAxis(1),driveStick.getRawAxis(4));
    }

    @Override
    public double getStatus() 
    {
        return 0;
    }

    @Override
    public void init() 
    {

    }
    @Override
    public void end()
    {
        drive.getLeftMotor().setSelectedSensorPosition(0);
        drive.getRightMotor().setSelectedSensorPosition(0);
    }
}
