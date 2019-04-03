package frc.commands.subsystem.hatch;

import frc.commands.AutonCommand;
import frc.subsystem.Sucker;

public class CmdSetSuck implements AutonCommand
{
    private Sucker sucker;
    private int suck;
    public CmdSetSuck(Sucker iSucker, int iSuck)
    {
        suck = iSuck;
        sucker = iSucker;
    }

    @Override
    public boolean isFinished() 
    {
        sucker.set(suck);
        return true;
    }

    @Override
    public void runTask() 
    {
    }

    @Override
    public double getStatus() {
        return 0;
    }

    @Override
    public void init() {

    }

}