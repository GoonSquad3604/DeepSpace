package frc.auton.hatch;


import edu.wpi.first.wpilibj.Relay.Value;
import frc.commands.special.CmdMerge;
import frc.commands.special.CmdMergeOneDone;
import frc.commands.special.CmdWait;
import frc.commands.subsystem.CmdMoveElevator;
import frc.commands.subsystem.hatch.CmdSetHatch;
import frc.commands.subsystem.hatch.CmdSetSuck;
import frc.commands.subsystem.hatch.CmdSuck;
import frc.commands.subsystem.hatch.CmdToggleRelay;
import frc.subsystem.ArticulatorState;
import frc.commands.drive.*;
import frc.auton.Auton;
import static frc.robot.Constants.*;

public class PanelAutonPlace
{
    public static void addCommands(Auton auton)
    {

        auton.addCommand(new CmdDriveStop(auton.getDrive()));

        auton.addCommand(new CmdSetSuck(auton.getSucker(), 0));

        auton.addCommand(new CmdToggleRelay(auton, auton.getSucker(), Value.kForward));

        auton.addCommand(new CmdMergeOneDone(
            new CmdWait(0.25),
            new CmdSetHatch(auton.getHatchManipulator(), ArticulatorState.kHatch, auton)
        ));

        auton.addCommand(new CmdMerge(
            new CmdManualDrive(auton.getDrive(), auton.getDriveStick(), auton.getOperateStick(), auton),   
            new CmdSetHatch(auton.getHatchManipulator(), ArticulatorState.kHatch, auton)
        ));

        auton.addCommand(new CmdMerge(
            new CmdManualDrive(auton.getDrive(), auton.getDriveStick(), auton.getOperateStick(), auton),
            new CmdToggleRelay(auton, auton.getSucker(), Value.kOff)
        ));
    } 
}