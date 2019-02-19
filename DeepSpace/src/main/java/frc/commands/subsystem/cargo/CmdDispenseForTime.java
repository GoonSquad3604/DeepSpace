package frc.commands.subsystem.cargo;

import edu.wpi.first.wpilibj.Timer;
import frc.commands.AutonCommand;
import frc.subsystem.CargoManipulator;

public class CmdDispenseForTime implements AutonCommand
{
    private Timer timer;
    private CargoManipulator cargo;
    private double time;

    public CmdDispenseForTime(double iTime, CargoManipulator iCargo)
    {
        if(iCargo == null)
        {
            throw new NullPointerException("CARGO MANIPULATOR NOT FOUND!");
        }
        cargo = iCargo;
        time = iTime;
        timer = new Timer();
    }

    @Override
    public boolean isFinished() 
    {
        return timer.get() >= time;
    }

    @Override
    public void runTask() 
    {
        cargo.runDispense();
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
    public double getTime(){
        return time;
    }

    @Override
    public void end()
    {
        cargo.stop();
    }

}