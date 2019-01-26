package frc.auton;
import frc.auton.commands.CmdDumbSpin;
//
//A higly dumb auton. It is very dumb.
public class DumbAuton
{
    public static void addCommands(Auton auton) 
    {
        auton.addCommand(new CmdDumbSpin(auton.getDrive())); //does some of the gibraltar autons except intentionally
    }
}