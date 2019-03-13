package frc.auton;
import static frc.robot.Constants.*;

import frc.commands.special.CmdMerge;
import frc.commands.subsystem.CmdMoveElevator;
import frc.commands.subsystem.cargo.CmdMoveHinge;
import frc.commands.drive.CmdManualDrive;
public class PickupPanelAuton
{
    public static void addCommands(Auton auton)
    {
        //Sets Hinge to 0
        auton.addCommand(new CmdMerge(
            new CmdMoveHinge(0,1,auton.getCargoManipulator()),
            new CmdManualDrive(auton.getDrive(),auton.getDriveStick(),auton.getOperateStick(),auton,false)
        ));
        //Deploy Hook
        auton.addCommand(new CmdMerge(
            null /*TODO replace with deploy hook*/,
            new CmdManualDrive(auton.getDrive(),auton.getDriveStick(),auton.getOperateStick(),auton,false)
        ));
        //Slightly Moves Elevator
        double destination = auton.getElevator().getHeight() + kHatchMovementPickUp;
        auton.addCommand(new CmdMerge(
            new CmdMoveElevator(destination,auton.getElevator()),
            new CmdManualDrive(auton.getDrive(),auton.getDriveStick(),auton.getOperateStick(),auton,false)
        ));
        //Retract Hook
        auton.addCommand(new CmdMerge(
            null /*TODO replace with retract hook*/,
            new CmdManualDrive(auton.getDrive(),auton.getDriveStick(),auton.getOperateStick(),auton,false)
        ));
    } 
}