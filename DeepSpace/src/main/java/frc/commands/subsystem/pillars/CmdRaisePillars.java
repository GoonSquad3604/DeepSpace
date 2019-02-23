package frc.commands.subsystem.pillars;


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
        if(positionRear - positionFront > 1)
        {
            pillars.setFrontPillar(1);
        }
        else
        {
            pillars.setFrontPillar(0.9);
        }
        
        if(positionFront - positionRear > 1)
        {
            pillars.setRearPillar(1);
        }
        else
        {
            pillars.setRearPillar(0.9);
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