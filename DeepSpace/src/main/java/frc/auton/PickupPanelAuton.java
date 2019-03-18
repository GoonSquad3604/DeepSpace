package frc.auton;
import static frc.robot.Constants.*;

import frc.commands.special.CmdMerge;
import frc.commands.subsystem.CmdMoveElevator;
import frc.commands.subsystem.cargo.CmdMoveHinge;
import frc.commands.subsystem.hatch.CmdMovePickup;
import frc.commands.drive.CmdDriveTime;
import frc.commands.drive.CmdManualDrive;
public class PickupPanelAuton
{
    public static void addCommands(Auton auton)
    {
        //auton.addCommand(new CmdDriveTime(0.5, 0.5, auton.getDrive()));
        auton.addCommand(new CmdMerge(            
            new CmdManualDrive(auton.getDrive(),auton.getDriveStick(),auton.getOperateStick(),auton),
            new CmdMovePickup(auton.getHatchManipulator())
                                    
        ));
        auton.addCommand(new CmdMoveElevator(kHatchFeederUp, auton.getElevator()));
        auton.addCommand(new CmdDriveTime(0.5, -0.5, auton.getDrive()));
    } 
}