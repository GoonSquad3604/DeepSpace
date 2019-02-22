package frc.commands.special;

import edu.wpi.first.wpilibj.Timer;
import frc.commands.AutonCommand;

public class CmdWait implements AutonCommand
{
    
    //Just waits
    private Timer timer;
    private double time;
    
    public CmdWait(double iTime)
    {
        timer = new Timer();
        time = iTime;
    }

    @Override
    public boolean isFinished() 
    {
        return timer.get() > time;
    }

    @Override
    public void runTask() 
    {

    }

    @Override
    public double getStatus() 
    {
        return 0;
    }

    @Override
    public void init() 
    {
        timer.start();
        timer.reset();
    }

}