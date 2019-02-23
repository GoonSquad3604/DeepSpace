package frc.commands.subsystem.pillars;

import frc.commands.AutonCommand;
import frc.subsystem.Pillars;

public class CmdLevelPillars implements AutonCommand
{
    private Pillars pillars;
    private double initPositionFront;
    private double positionFront;
    private double initPositionRear;
    private double positionRear;
    
    public CmdLevelPillars(Pillars iPillars)
    {
        pillars = iPillars;
    }
    
    @Override
    public boolean isFinished() 
    {
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
        return true;
    }

    @Override
    public void runTask() 
    {
        
    }

    @Override
    public double getStatus() 
    {
        return 0;
    }

    @Override
    public void init() 
    {
        initPositionFront = pillars.getFrontHeight();
        initPositionRear = pillars.getRearHeight();
    }

    @Override
    public void end()
    {
        pillars.setPillars(0);
    }

}