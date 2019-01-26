package frc.commands.drive;

import edu.wpi.first.wpilibj.Timer;
import frc.commands.AutonCommand;
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
        drive.arcadeDrive(-0.6,0);
    }

    @Override
    public double getStatus() {
        return 0;
    }

    @Override
    public void init() {
        t.reset();
    }

}