package frc.commands.subsystem;

import frc.commands.AutonCommand;
import frc.subsystem.Pillars;

public class CmdRaisePillars implements AutonCommand
{
    //Raises pillars to a specific height at a specific speed.
    private Pillars pillars;
    private double height;
    private double speed;
    public CmdRaisePillars(double iHeight, double iSpeed, Pillars iPillars)
    {
        pillars = iPillars;
        speed = Math.abs(iSpeed);
        height = iSpeed;
    }
    @Override
    public boolean isFinished() 
    {
        return pillars.getDistance() >= height;
    }

    @Override
    public void runTask() 
    {
        pillars.setFrontPillar(speed);
        pillars.setRearPillar(speed);
        //Attempts to adjust for faster rear pillars.
        if(pillars.getRearHeight() > pillars.getFrontHeight()+5)
        {
            pillars.setRearPillar(speed/2);
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