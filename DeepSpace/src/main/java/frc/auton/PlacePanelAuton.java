package frc.auton;
import static frc.robot.Constants.*;

import frc.commands.special.CmdMerge;
import frc.commands.subsystem.CmdMoveElevator;
import frc.commands.drive.CmdManualDrive;
public class PlacePanelAuton
{
    public static void addCommands(Auton auton)
    {
        double destination = auton.getElevator().getHeight() - kHatchMovementPlace;
        auton.addCommand(new CmdMerge(
            new CmdMoveElevator(destination,auton.getElevator()),
            new CmdManualDrive(auton.getDrive(),auton.getDriveStick(),auton.getOperateStick(),auton)
        ));
    } 
}