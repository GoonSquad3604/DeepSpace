package frc.auton.hatch;

import frc.commands.drive.CmdManualDrive;
import frc.commands.special.CmdMergeAdd;
import frc.commands.subsystem.CmdMoveElevator;
import frc.auton.Auton;

public class HatchPlaceAuton2
{

    static CmdMergeAdd cmdMergeAdd;

    public static void addCommands(Auton auton, double elevatorHeight)
    {
        cmdMergeAdd = new CmdMergeAdd(
                new CmdManualDrive(auton.getDrive(),auton.getDriveStick(),auton.getOperateStick(), auton, cmdMergeAdd),
                new CmdMoveElevator(elevatorHeight, auton.getElevator())
        );

        auton.addCommand(cmdMergeAdd);
    }

}