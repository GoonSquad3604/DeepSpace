package frc.commands.drive;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.commands.AutonCommand;
import frc.subsystem.drivetrain.DriveTrain;

public class CmdDriveTime implements AutonCommand
{
    //Drives the robot forwards based on a timer.
    private Timer timer;
    private double time;
    private DriveTrain drive;
    
    public CmdDriveTime(double iTime, DriveTrain iDriveTrain)
    {
        timer = new Timer();
        time = iTime;
        drive = iDriveTrain;
    }

    @Override
    public boolean isFinished() {
        if(t.get()>time)
        {
            drive.arcadeDrive(0,0);
            System.out.println("TAAAAAAAKE ONNNNNNNN MEEEEE");
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public void runTask() {
        drive.arcadeDrive(0.4,0);
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
        drive.arcadeDrive(0,0);
    }

}