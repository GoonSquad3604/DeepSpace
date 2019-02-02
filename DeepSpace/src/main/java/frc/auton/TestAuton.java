package frc.auton;
import frc.commands.*;
import frc.commands.drive.*;
import frc.commands.special.CmdWait;
import jaci.pathfinder.Waypoint;

public class TestAuton
{
    public static void addCommands(Auton auton) 
    {
        auton.addCommand(new CmdDriveTime(1,auton.getDrive()));
        auton.addCommand(new CmdDriveReverse(1.3,auton.getDrive()));
        auton.addCommand(new CmdWait(2));
        auton.addCommand(new CmdDriveTime(0.5,auton.getDrive()));
        auton.addCommand(new CmdMoveToWaypoint(auton.getDrive(),new Waypoint(0,0,0),new Waypoint(1,0,0)));
    }
}