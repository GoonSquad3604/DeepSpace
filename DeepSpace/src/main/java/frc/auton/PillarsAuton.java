package frc.auton;

import static frc.robot.Constants.*;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import frc.commands.subsystem.pillars.*;
import frc.commands.drive.*;
import frc.commands.special.*;

public class PillarsAuton
{
    public static void addCommands(Auton auton)
    {
        //Rumbles Sticks
        auton.getOperateStick().setRumble(RumbleType.kLeftRumble,1);
        auton.getOperateStick().setRumble(RumbleType.kRightRumble,1);
        auton.getDriveStick().setRumble(RumbleType.kLeftRumble,1);
        auton.getDriveStick().setRumble(RumbleType.kRightRumble,1);
        //Actul Climb Stuff
        auton.addCommand(new CmdRaisePillars(kThirdLevel,auton.getPillars()));
        auton.addCommand(new CmdMovePillarWheelsTime(1.5, 1, auton.getPillars()));
        auton.addCommand(new CmdLowerSinglePillar(PillarType.kFrontPillar, 0, 1, auton.getPillars()));
        auton.addCommand(new CmdDriveTime(1, auton.getDrive()));
        auton.addCommand(new CmdLowerSinglePillar(PillarType.kRearPillar, 0, 1, auton.getPillars()));
        auton.addCommand(new CmdDriveTime(1, auton.getDrive()));
    }
}