package frc.auton.commands;

import edu.wpi.first.wpilibj.Timer;
import frc.subsystem.DriveTrain;

public class CmdDriveReverse implements AutonCommand
{
    //Drives the robot backwards based on a timer.
    Timer t;
    double time;
    private DriveTrain drive;
    public CmdDriveReverse(double time, DriveTrain drive)
    {
        t = new Timer();
        this.time = time;
        this.drive = drive;

    }
    @Override
    public boolean isFinished() {
        if(t.get()>time)
        {
            System.out.println("DON'T STOP ME NOOOOWWW...even though I will stop now");
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public void runTask() {
        drive.arcadeDrive(0.6,0);
    }

    @Override
    public double getStatus() {
        return 0;
    }

    @Override
    public void init() {
        t.reset();
        t.start();
    }

}