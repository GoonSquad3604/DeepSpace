package frc.auton;
import frc.auton.commands.CmdDumbSpin;
//
//A higly dumb auton. It is very dumb.
public class DumbAuton extends Auton
{
    public DumbAuton(Object... subsystems)
    {
        super(subsystems);
    }
    @Override
    protected void addCommands() 
    {
        addCommand(new CmdDumbSpin(drive)); //does some of the gibraltar autons except intentionally
    }
}