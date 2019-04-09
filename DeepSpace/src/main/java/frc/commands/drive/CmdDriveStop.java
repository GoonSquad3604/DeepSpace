package frc.commands.drive;

import edu.wpi.first.wpilibj.Timer;
import frc.commands.AutonCommand;
import frc.subsystem.drivetrain.DriveTrain;

public class CmdDriveStop implements AutonCommand
{
    private DriveTrain driveTrain;
    
    
    /**
     * Drives the robot forwards based on a timer
     * @param iTime Amount of time to drive (seconds)
     * @param iDriveTrain Drive Train Object
     */
    public CmdDriveStop(DriveTrain iDriveTrain)
    {
        driveTrain = iDriveTrain;
    }

    @Override
    public boolean isFinished() {
        
        driveTrain.arcadeDrive(0, 0);
        return true;

    }

    @Override
    public void runTask() {
        
    }

    @Override
    public double getStatus() {
        return 0;
    }

    @Override
    public void init() {
    }

    @Override
    public void end()
    {
        driveTrain.arcadeDrive(0,0);
    }

}