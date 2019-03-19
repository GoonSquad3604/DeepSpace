package frc.auton;
import static frc.robot.Constants.*;
import frc.commands.drive.CmdManualDrive;
import frc.commands.special.CmdMerge;
import frc.commands.subsystem.hatch.CmdToggleHatch;

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