package frc.auton.elevator;

import frc.auton.Auton;
import frc.commands.drive.CmdManualDrive;
import frc.commands.special.CmdMerge;
import frc.commands.subsystem.CmdResetElevator;

public class ResetElevatorAuton
{
    public static void addCommands(Auton auton)
    {
        auton.addCommand(new CmdMerge(
            new CmdManualDrive(auton.getDrive(),auton.getDriveStick(),auton.getOperateStick(),auton),
            new CmdResetElevator(auton.getElevator()))
        );
    }
}