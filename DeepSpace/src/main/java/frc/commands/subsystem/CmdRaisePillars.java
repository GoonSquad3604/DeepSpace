package frc.commands.subsystem;

import frc.commands.AutonCommand;
import frc.subsystem.Pillars;

public class CmdRaisePillars implements AutonCommand
{
    //Raises pillars to a specific height at a specific speed.
    private Pillars pillars;
    private double height;
    private double speed;

    private double frontInitPosition;
    private double rearInitPosition;
    public CmdRaisePillars(double iHeight, double iSpeed, Pillars iPillars)
    {
        pillars = iPillars;
        speed = Math.abs(iSpeed);
        height = iHeight;
    }
    @Override
    public boolean isFinished() 
    {   
        return pillars.getHeight() >= height;
    }

    @Override
    public void runTask() 
    {

        
        //Attempts to adjust for faster rear pillars.
        if(pillars.getRearHeight() - rearInitPosition > pillars.getFrontHeight() - frontInitPosition)
        {
            pillars.setFrontPillar(0.65);
            pillars.setRearPillar(0.5);
        }
        else
        {
            pillars.setFrontPillar(speed);
            pillars.setRearPillar(speed);
        }
    }

    @Override
    public double getStatus() 
    {
        return pillars.getHeight();
    }

    @Override
    public void init() {
        frontInitPosition = pillars.getFrontHeight();
        rearInitPosition = pillars.getRearHeight();

    }
    @Override
    public void end()
    {
        pillars.setPillars(0);
    }

}