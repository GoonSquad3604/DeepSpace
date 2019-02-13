package frc.commands.subsystem;

import frc.commands.AutonCommand;
import frc.subsystem.Pillars;

public class CmdLevelPillars implements AutonCommand
{
    Pillars pillars;
    public CmdLevelPillars(Pillars iPillars)
    {
        pillars = iPillars;
    }
    @Override
    public boolean isFinished() 
    {
        pillars.setPillars(0.1);
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

    }

    @Override
    public void end()
    {

    }

}