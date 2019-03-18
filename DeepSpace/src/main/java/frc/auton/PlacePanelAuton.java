package frc.auton;
import static frc.robot.Constants.*;

import frc.commands.special.CmdMerge;
import frc.commands.subsystem.CmdMoveElevator;
import frc.commands.drive.*;

public class PlacePanelAuton
{
    public static void addCommands(Auton auton)
    {
        double destination = auton.getElevator().getHeight() - kHatchMovementPlace;
        auton.addCommand(new CmdMoveElevator(destination, auton.getElevator()));
        auton.addCommand(new CmdDriveTime(0.5, -0.5, auton.getDrive()));
    } 
}