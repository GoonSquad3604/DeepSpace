package frc.commands.subsystem;

import frc.commands.AutonCommand;
import frc.subsystem.Pillars;

public class CmdMovePillarWheels implements AutonCommand
{

    private Pillars pillars;
    private double distance;
    private final double speed;
    public CmdMovePillarWheels(double distance, double speed, Pillars pillars)
    {
        if(pillars == null)
        {
            throw new NullPointerException("PILLARS NOT FOUND");
        }
        this.pillars = pillars;
        this.distance = distance;
        this.speed = speed;
    }
    @Override
    public boolean isFinished() 
    {
        return pillars.getDistance() >= distance;
    }

    @Override
    public void runTask() 
    {
        pillars.moveWheels(speed);
    }

    @Override
    public double getStatus() 
    {
        return pillars.getDistance();
    }

    @Override
    public void init() 
    {

    }
    @Override
    public void end()
    {
        pillars.moveWheels(0);
    }

}