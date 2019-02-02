package frc.commands.subsystem;

import frc.commands.AutonCommand;
import frc.subsystem.Pillars;

public class CmdMovePillarWheels implements AutonCommand
{

    private Pillars pillars;
    double distance;
    public CmdMovePillarWheels(double distance, Pillars pillars)
    {
        if(pillars == null)
        {
            throw new NullPointerException("PILLARS NOT FOUND");
        }
        this.pillars = pillars;
        this.distance = distance;
    }
    @Override
    public boolean isFinished() 
    {
        return pillars.getDistance() >= distance;
    }

    @Override
    public void runTask() 
    {
        pillars.moveWheels(distance);
    }

    @Override
    public double getStatus() 
    {
        return 0;
    }

    @Override
    public void init() 
    {

    }

}