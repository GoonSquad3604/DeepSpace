package frc.commands.drive;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.commands.AutonCommand;
import frc.subsystem.drivetrain.DriveTrain;

public class CmdDriveDistance implements AutonCommand
{
    //Drives the robot forwards based on a timer.
    private double distance;
    private DriveTrain drive;
    
    public CmdDriveDistance(double iDistance, DriveTrain iDrive)
    {
        distance = iDistance;
        drive = iDrive;
    }

    @Override
    public boolean isFinished() 
    {
        if((Math.abs(drive.getLeftInches())+Math.abs(drive.getRightInches())) / 2 >= distance)
        {
            drive.arcadeDrive(0,0);
            System.out.println("TAAAAAAAKE ONNNNNNNN MEEEEE");
            return true;
        }
        return false;
    }

    @Override
    public void runTask() 
    {
        drive.arcadeDrive(-0.35,0);
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
        drive.arcadeDrive(0,0);
    }

}