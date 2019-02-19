package frc.commands.subsystem.pillars;

import java.util.ArrayList;

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
        
        double plrSpeed = ((pillars.getRearHeight() - rearInitPosition) 
        - (pillars.getFrontHeight() - frontInitPosition) > 1) ? 0.8 : 0.7; 
        
        pillars.setFrontPillar(plrSpeed);
        pillars.setRearPillar(0.7);
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