package frc.auton.full;

import frc.auton.*;
import frc.commands.subsystem.*;
import frc.commands.drive.*;
import frc.commands.special.*;
import static frc.robot.Constants.*;
public class HatchPanelRocket_FullAuton
{
    public static void addCommands(Auton auton)
    {
        //auton.addCommand(new CmdMoveToWaypoint(auton.getDrive(),null)); //TODO specify waypoints
        HatchPlaceAuton.addCommands(auton, kBottomRocketHatch);
    }
}