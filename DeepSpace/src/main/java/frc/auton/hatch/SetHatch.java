package frc.auton.hatch;

import frc.commands.drive.CmdManualDrive;
import frc.commands.special.CmdMerge;
import frc.commands.subsystem.hatch.CmdSetHatch;
import frc.commands.subsystem.hatch.CmdToggleHatch;
import frc.subsystem.ArticulatorState;
import frc.auton.Auton;
import static frc.robot.Constants.*;

public class SetHatch
{

    public static void addCommands(Auton auton, ArticulatorState state)
    {
        auton.addCommand(
            new CmdMerge(
                new CmdSetHatch(auton.getHatchManipulator(), state, auton),
                new CmdManualDrive(auton.getDrive(), auton.getDriveStick(), auton.getOperateStick(), auton)
            )
        );
    }

}