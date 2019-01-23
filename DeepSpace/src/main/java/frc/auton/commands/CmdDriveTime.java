package frc.auton.commands;

import javax.lang.model.util.ElementScanner6;

import edu.wpi.first.wpilibj.Timer;
import frc.robot.Robot;

public class CmdDriveTime implements AutonCommand
{
    Timer t;
    double time;
    public CmdDriveTime(double time)
    {
        t = new Timer();
        t.start();
        this.time = time;
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
        Robot.getDriveTrain().arcadeDrive(-0.6,0);
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