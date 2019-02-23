package frc.commands.subsystem.pillars;

import frc.commands.AutonCommand;
import frc.subsystem.Pillars;

public class CmdMovePillarWheels implements AutonCommand
{

    private Pillars pillars;
    private double distance;
    private double speed;
    
    public CmdMovePillarWheels(double iDistance, double iSpeed, Pillars iPillars)
    {
        if(pillars == null)
        {
            throw new NullPointerException("PILLARS NOT FOUND");
        }
        pillars = iPillars;
        distance = iDistance;
        speed = iSpeed;
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