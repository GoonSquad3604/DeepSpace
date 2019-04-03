package frc.auton.hatch;

import com.revrobotics.CANSparkMax.IdleMode;
import frc.commands.subsystem.CmdMoveElevator;
import frc.commands.drive.CmdDriveTime;
import frc.auton.Auton;
import static frc.robot.Constants.*;

public class PickupPanelAuton
{
    public static void addCommands(Auton auton)
    {
        //auton.addCommand(new CmdDriveTime(0.5, 0.5, auton.getDrive()));
        // auton.addCommand(new CmdMerge(            
        //     new CmdManualDrive(auton.getDrive(),auton.getDriveStick(),auton.getOperateStick(),auton),
        //     new CmdMovePickup(auton.getHatchManipulator())
                                    
        // ));
        auton.getDrive().setMotorMode(IdleMode.kBrake);
        auton.addCommand(new CmdMoveElevator(kHatchFeederUp, auton.getElevator()));
        auton.addCommand(new CmdDriveTime(0.5, -0.5, auton.getDrive()));
        auton.getDrive().setMotorMode(IdleMode.kCoast);
    } 
}