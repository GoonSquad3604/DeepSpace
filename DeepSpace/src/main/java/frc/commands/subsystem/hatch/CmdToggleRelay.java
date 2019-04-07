package frc.commands.subsystem.hatch;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;
import frc.auton.Auton;
import frc.commands.AutonCommand;
import frc.subsystem.Sucker;
public class CmdToggleRelay implements AutonCommand
{
    private Sucker sucker;
    private Value value;
    private Auton auton;

    public CmdToggleRelay(Auton iAuton, Sucker iSucker, Relay.Value iValue)
    {
        sucker = iSucker;
        value = iValue;
        auton = iAuton;
    }

    @Override
    public boolean isFinished() {
        if(value == Value.kForward)
        {
            auton.getHatchManipulator().setHatch(false);
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