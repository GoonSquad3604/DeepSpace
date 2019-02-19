package frc.auton;

import frc.commands.drive.CmdManualDrive;
import frc.commands.special.CmdMerge;
import frc.commands.subsystem.cargo.CmdDispenseForTime;
import frc.commands.subsystem.CmdMoveElevator;
public class CargoPlaceAuton
{

    public static void addCommands(Auton auton, double elevatorHeight)
    {
        auton.addCommand(new CmdMerge(
            new CmdManualDrive(auton.getDrive(),auton.getDriveStick()),
            new CmdMoveElevator(elevatorHeight, auton.getElevator())));
        auton.addCommand(new CmdMerge(
            new CmdManualDrive(auton.getDrive(),auton.getDriveStick()),
            new CmdDispenseForTime( 0.25, auton.getCargoManipulator())));
        auton.addCommand(new CmdMerge(
            new CmdManualDrive(auton.getDrive(),auton.getDriveStick()),
            new CmdMoveElevator(100, auton.getElevator())));
    }

}