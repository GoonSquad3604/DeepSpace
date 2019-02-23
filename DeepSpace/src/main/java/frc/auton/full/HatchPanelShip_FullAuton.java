package frc.auton.full;

import frc.auton.*;
import frc.commands.subsystem.*;
import frc.commands.drive.*;
import frc.commands.special.*;
import static frc.robot.Constants.*;
public class HatchPanelShip_FullAuton
{
    public static void addCommands(Auton auton)
    {
        auton.addCommand(new CmdMoveToWaypoint(auton.getDrive(),null,"a")); //TODO specify waypoints
<<<<<<< HEAD:DeepSpace/src/main/java/frc/auton/full/HatchPanelCargo_FullAuton.java
        HatchPlaceAuton.addCommands(auton, kCargoLevelHatch);
=======
        HatchPlaceAuton.addCommands(auton, kBottomRocketHatch);
>>>>>>> 1fdb2ca0fba7f5bc4607f435d7efdb52083d5fce:DeepSpace/src/main/java/frc/auton/full/HatchPanelShip_FullAuton.java
    }
}