package frc.commands.subsystem.hatch;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;
import frc.commands.AutonCommand;
import frc.subsystem.Sucker;
public class CmdToggleRelay implements AutonCommand
{
    Sucker sucker;
    Value value;
    public CmdToggleRelay(Sucker iSucker, Relay.Value iValue)
    {
        sucker = iSucker;
        value = iValue;
    }

    @Override
    public boolean isFinished() {
        if(value == Value.kForward)
        {
            sucker.relayOn();
        }
        else
        {
            sucker.relayOff();
        }
        return true;
    }

    @Override
    public void runTask() {

    }

    @Override
    public double getStatus() {
        return 0;
    }

    @Override
    public void init() {

    }

}