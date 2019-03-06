package frc.commands.subsystem.pillars;

import frc.commands.AutonCommand;
import frc.subsystem.Pillars;

public class CmdZeroPillars implements AutonCommand
{
    private Pillars pillars;
    private double initPositionFront;
    private double positionFront;
    private double initPositionRear;
    private double positionRear;
    
    public CmdZeroPillars(Pillars iPillars)
    {
        pillars = iPillars;
    }
    
    @Override
    public boolean isFinished() 
    {
        return Math.abs(pillars.getFrontHeight()) < 0.1 && Math.abs(pillars.getRearHeight()) < 0.1;
    }

    @Override
    public void runTask() 
    {
        if(pillars.getFrontHeight() < 0.1)
        {
            pillars.setFrontPillar(0.1);
        }
        else if(pillars.getFrontHeight() > -0.1)
        {
            pillars.setFrontPillar(-0.1);
        }
        else
        {
            pillars.setFrontPillar(0);
        }

        if(pillars.getRearHeight() < 0.1)
        {
            pillars.setRearPillar(0.1);
        }
        else if(pillars.getRearHeight() > -0.1)
        {
            pillars.setRearPillar(-0.1);
        }
        else
        {
            pillars.setRearPillar(0);
        }
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

    @Override
    public void end()
    {
        pillars.setPillars(0);
    }

}