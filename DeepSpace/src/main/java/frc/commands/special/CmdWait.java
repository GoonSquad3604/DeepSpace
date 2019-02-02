package frc.commands.special;

import edu.wpi.first.wpilibj.Timer;
import frc.commands.AutonCommand;

public class CmdWait implements AutonCommand
{
    
    //Just waits
    private Timer t;
    private double time;
    
    public CmdWait(double time)
    {
        t = new Timer();
        t.start();
        this.time = time;
    }

    @Override
    public boolean isFinished() 
    {
        return t.get() > time;
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
        t.reset();
    }

}