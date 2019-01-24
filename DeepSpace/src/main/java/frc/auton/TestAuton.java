package frc.auton;
import frc.auton.commands.*;
import jaci.pathfinder.Waypoint;
//
public class TestAuton extends Auton
{
    @Override
    protected void addCommands() {
        addCommand(new CmdDriveTime(1,drive));
        addCommand(new CmdDriveReverse(1.3,drive));
        addCommand(new CmdWait(2));
        addCommand(new CmdDriveTime(0.5,drive));
        addCommand(new CmdMoveToWaypoint(drive,new Waypoint(0,0,0),new Waypoint(1,0,0)));
    }
}