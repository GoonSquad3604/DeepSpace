package frc.commands.drive;

import edu.wpi.first.wpilibj.Timer;
import frc.commands.AutonCommand;
import frc.subsystem.drivetrain.DriveTrain;

public class CmdDriveTime implements AutonCommand
{
    //Drives the robot forwards based on a timer.
    private Timer timer;
    private double time;
    private DriveTrain driveTrain;
    
    public CmdDriveTime(double iTime, DriveTrain iDriveTrain)
    {
        timer = new Timer();
        time = iTime;
        driveTrain = iDriveTrain;
    }

    @Override
    public boolean isFinished() {
        
        if(timer.get() > time)
        {
            driveTrain.arcadeDrive(0,0);
            return true;
        }
        else
        {
            return false;
        }

    }

    @Override
    public void runTask() {
        driveTrain.arcadeDrive(0.3,0);
    }

    @Override
    public double getStatus() {
        return 0;
    }

    @Override
    public void init() {
        timer.start();
        timer.reset();
    }
    
    @Override
    public void end()
    {
        driveTrain.arcadeDrive(0,0);
    }

}