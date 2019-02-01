package frc.commands.drive;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.commands.AutonCommand;
import frc.subsystem.drivetrain.DriveTrain;

public class CmdDriveReverse implements AutonCommand
{
    //Drives the robot backwards based on a timer.
    Timer t;
    double time;
    private DifferentialDrive drive;
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