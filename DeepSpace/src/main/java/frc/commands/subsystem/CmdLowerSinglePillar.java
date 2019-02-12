package frc.commands.subsystem;

import frc.commands.AutonCommand;
import frc.subsystem.Pillars;

public class CmdLowerSinglePillar implements AutonCommand
{
    //Receives the height, speed, pillars object, and a boolean that determines whether or not to use the first pillar.
    //Lowers a single set of pillars;
    private Pillars pillars;
    private double height;
    private double speed;
    private boolean front;
    public CmdLowerSinglePillar(boolean iFront, double iHeight, double iSpeed, Pillars iPillars)
    {
        pillars = iPillars;
        speed = -Math.abs(iSpeed);
        height = iSpeed;
        front = iFront;
    }
    @Override
    public boolean isFinished() 
    {
        if(front)
        {
            return pillars.getFrontHeight() <= height;
        }
        else
        {
            return pillars.getRearHeight() <= height;
        }
    }

    @Override
    public void runTask() 
    {
        if(front)
        {
            pillars.setFrontPillar(-speed);
        }
        else
        {
            pillars.setRearPillar(-speed);
        }
    }

    @Override
    public double getStatus() 
    {
        return pillars.getDistance();
    }

    @Override
    public void init() {

    }
    @Override
    public void end()
    {
        pillars.setPillars(0);
    }

}