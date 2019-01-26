package frc.commands;

import edu.wpi.first.wpilibj.Timer;

public class CmdWait implements AutonCommand
{
    //Just waits
    Timer t;
    double time;
    public CmdWait(double time)
    {
        t = new Timer();
        t.start();
        this.time = time;
    }
    @Override
    public boolean isFinished() {
        if(t.get()>time)
        {
            return true;
        }
        else
        {
            return false;
        }
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
        t.reset();
    }

}