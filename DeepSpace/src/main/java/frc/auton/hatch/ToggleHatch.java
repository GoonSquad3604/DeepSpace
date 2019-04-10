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

        auton.setIsHatchCommand(true);

        auton.addCommand(
            new CmdMerge(
                new CmdToggleHatch(auton.getHatchManipulator(), auton),
                new CmdManualDrive(auton.getDrive(), auton.getDriveStick(), auton.getOperateStick(), auton)
            )
        );

        auton.setIsHatchCommand(false);

    }

}