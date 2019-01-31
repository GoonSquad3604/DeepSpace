package frc.commands.drive;

import edu.wpi.first.wpilibj.Timer;
import frc.commands.AutonCommand;
import frc.robot.Constants;
import frc.subsystem.DriveTrain;

public class CmdDriveTime implements AutonCommand
{
    //Drives the robot forwards based on a timer.
    private Timer t;
    private double time;
    private DriveTrain drive;
    public CmdDriveTime(double time, DriveTrain drive)
    {
        t = new Timer();
        t.start();
        this.time = time;
        this.drive = drive;
    }
    @Override
    public boolean isFinished() {
        if(t.get()>time)
        {
            drive.setDrive(0);
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
        drive.arcadeDrive(-0.4,Constants.kIgnoreDrive);
    }

    @Override
    public double getStatus() {
        return 0;
    }

    @Override
    public void init() {
        t.reset();
    }
    @Override
    public void end()
    {
        drive.setDrive(0);
    }

}