package frc.auton.hatch;

import frc.commands.drive.CmdManualDrive;
import frc.commands.special.CmdMerge;
import frc.commands.subsystem.hatch.CmdToggleHatch;
import frc.auton.Auton;
import static frc.robot.Constants.*;

public class ToggleHatch
{

    public static void addCommands(Auton auton)
    {
        auton.addCommand(
            new CmdMerge(
                new CmdToggleHatch(auton.getHatchManipulator()),
                new CmdManualDrive(auton.getDrive(), auton.getDriveStick(), auton.getOperateStick(), auton)
            )
        );

    }

}