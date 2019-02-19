package frc.commands.drive;

import edu.wpi.first.wpilibj.XboxController;
import frc.commands.AutonCommand;
import frc.subsystem.drivetrain.DriveTrain;

public class CmdManualDrive implements AutonCommand
{

    private DriveTrain drive;
    private XboxController driveStick;
    public CmdManualDrive(DriveTrain iDrive, XboxController iDriveStick)
    {
        drive = iDrive;
        driveStick = iDriveStick;
    }
    @Override
    public boolean isFinished() 
    {
        drive.arcadeDrive(-driveStick.getRawAxis(1),driveStick.getRawAxis(4));
        return true;
    }

    @Override
    public void runTask() 
    {

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

}