package frc.auton.hatch;

import frc.commands.drive.CmdManualDrive;
import frc.commands.special.CmdMerge;
import frc.commands.subsystem.cargo.CmdDispenseForTime;
import frc.commands.subsystem.cargo.CmdMoveHinge;
import frc.commands.subsystem.CmdMoveElevator;
import frc.auton.Auton;

public class HatchPlaceAuton2
{

    public static void addCommands(Auton auton, double elevatorHeight)
    {
        auton.addCommand(new CmdMerge(
            new CmdManualDrive(auton.getDrive(),auton.getDriveStick(),auton.getOperateStick(),auton),
            new CmdMoveElevator(elevatorHeight, auton.getElevator(), auton)));
    }

}