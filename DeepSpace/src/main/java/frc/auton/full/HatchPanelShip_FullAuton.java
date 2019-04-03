package frc.auton.full;

import frc.auton.*;
import frc.auton.hatch.HatchPlaceAuton;
import frc.commands.subsystem.*;
import frc.commands.drive.*;
import frc.commands.special.*;
import static frc.robot.Constants.*;

public class HatchPanelShip_FullAuton
{
    public static void addCommands(Auton auton)
    {
        auton.addCommand(new CmdMoveToWaypoint(auton.getDrive(),null,"a")); //TODO specify waypoints
        HatchPlaceAuton.addCommands(auton, kBottomRocketHatch);
    }
}