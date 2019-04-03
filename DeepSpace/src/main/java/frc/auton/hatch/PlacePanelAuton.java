package frc.auton.hatch;


import edu.wpi.first.wpilibj.Relay.Value;
import frc.commands.special.CmdMerge;
import frc.commands.special.CmdWait;
import frc.commands.subsystem.CmdMoveElevator;
import frc.commands.subsystem.hatch.CmdToggleRelay;
import frc.commands.drive.*;
import frc.auton.Auton;
import static frc.robot.Constants.*;

public class PlacePanelAuton
{
    public static void addCommands(Auton auton)
    {
        auton.addCommand(new CmdMerge(
            new CmdManualDrive(auton.getDrive(), auton.getDriveStick(), auton.getOperateStick(), auton),
            new CmdToggleRelay(auton.getSucker(),Value.kForward)
        ));

        auton.addCommand(new CmdMerge(
            new CmdManualDrive(auton.getDrive(), auton.getDriveStick(), auton.getOperateStick(), auton),
            new CmdWait(1)
        ));

        auton.addCommand(new CmdMerge(
            new CmdManualDrive(auton.getDrive(), auton.getDriveStick(), auton.getOperateStick(), auton),
            new CmdToggleRelay(auton.getSucker(),Value.kOff)
        ));
    } 
}