package frc.auton.pillars;

import com.revrobotics.CANSparkMax.IdleMode;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import frc.commands.subsystem.pillars.*;
import frc.commands.drive.*;
import frc.commands.special.*;
import frc.auton.Auton;
import static frc.robot.Constants.*;

public class PillarsAuton
{
    public static void addCommands(Auton auton, double height)
    {
        //Rumbles Sticks
        auton.getOperateStick().setRumble(RumbleType.kLeftRumble, 1);
        auton.getOperateStick().setRumble(RumbleType.kRightRumble, 1);
        auton.getDriveStick().setRumble(RumbleType.kLeftRumble, 1);
        auton.getDriveStick().setRumble(RumbleType.kRightRumble, 1);
        auton.getDrive().setMotorMode(IdleMode.kBrake);
        //Actul Climb Stuff
        //auton.addCommand(new CmdZeroPillars(auton.getPillars()));
        auton.addCommand(new CmdRaisePillars(height, auton.getPillars()));
        auton.addCommand(new CmdMovePillarWheelsTime(1.5, 1, auton.getPillars()));
        auton.addCommand(new CmdLowerSinglePillar(PillarType.kFrontPillar, 11, 1, auton.getPillars()));
        auton.addCommand(new CmdDriveTime(1.5, 0.25, auton.getDrive()));
        auton.addCommand(new CmdLowerSinglePillar(PillarType.kRearPillar, 12, 1, auton.getPillars()));
        auton.addCommand(new CmdDriveTime(2, 0.25, auton.getDrive()));
    }


//Rumbles Sticks
// auton.getOperateStick().setRumble(RumbleType.kLeftRumble, 1);
// auton.getOperateStick().setRumble(RumbleType.kRightRumble, 1);
// auton.getDriveStick().setRumble(RumbleType.kLeftRumble, 1);
// auton.getDriveStick().setRumble(RumbleType.kRightRumble, 1);
// //auton.getDrive().setMotorMode(IdleMode.kBrake);
// //Actul Climb Stuff
// //auton.addCommand(new CmdZeroPillars(auton.getPillars()));
// auton.addCommand(new CmdRaisePillars(kSecondLevel, auton.getPillars()));
// auton.addCommand(new CmdMovePillarWheelsTime(1.5, 1, auton.getPillars()));
// auton.addCommand(new CmdLowerSinglePillar(PillarType.kFrontPillar, 8, 1, auton.getPillars()));
// auton.addCommand(new CmdDriveTime(1.5, auton.getDrive()));
// auton.addCommand(new CmdLowerSinglePillar(PillarType.kRearPillar, 8, 1, auton.getPillars()));
// // auton.addCommand(new CmdMerge(
// //     new CmdDriveTime(1.5, auton.getDrive()),
// //     new CmdLowerSinglePillar(PillarType.kRearPillar, 8, 1, auton.getPillars()))
// //                 );
// auton.addCommand(new CmdDriveTime(2, auton.getDrive()));









}