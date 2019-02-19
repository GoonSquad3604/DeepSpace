package frc.commands.subsystem.pillars;

import java.util.ArrayList;

import frc.commands.AutonCommand;
import frc.subsystem.Pillars;

public class CmdRaisePillars implements AutonCommand
{
    //Raises pillars to a specific height at a specific speed.
    private Pillars pillars;
    private double height;

    private double initPositionFront;
    private double initPositionRear;
    private double positionFront;
    private double positionRear;

    public CmdRaisePillars(double iHeight, Pillars iPillars)
    {
        height = iHeight;
        pillars = iPillars;
        initPositionFront = pillars.getFrontHeight();
        initPositionRear = pillars.getRearHeight();
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
        
        positionFront = pillars.getFrontHeight();
        positionRear = pillars.getRearHeight();
        if((positionRear - initPositionRear) - (positionFront - initPositionFront) > 1)
        {
            pillars.setFrontPillar(0.4);
        }
        else
        {
            pillars.setFrontPillar(0.0);
        }
        
        if( (positionFront - initPositionFront) - (positionRear - initPositionRear) > 1)
        {
            pillars.setRearPillar(0.4);
        }
        else
        {
            pillars.setRearPillar(0.0);
        }
    }

    @Override
    public double getStatus() 
    {
        return pillars.getHeight();
    }

    @Override
    public void init() {
        initPositionFront = pillars.getFrontHeight();
        initPositionRear = pillars.getRearHeight();
    }

    @Override
    public void end()
    {
        pillars.setPillars(0);
    }

}