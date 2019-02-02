package frc.commands.subsystem;

import edu.wpi.first.wpilibj.Timer;
import frc.commands.AutonCommand;
import frc.subsystem.CargoManipulator;

public class CmdDispenseForTime implements AutonCommand
{
    Timer timer;
    CargoManipulator cargo;
    double time;

    public CmdDispenseForTime(double time, CargoManipulator cargo)
    {
        if(cargo == null)
        {
            throw new NullPointerException("CARGO MANIPULATOR NOT FOUND!");
        }
        this.cargo = cargo;
        this.time = time;
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
    }

}