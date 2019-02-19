package frc.auton;
import static frc.robot.Constants.*;

import frc.commands.subsystem.pillars.*;
import frc.commands.drive.*;
import frc.commands.special.*;
public class PillarsAuton
{
    public static void addCommands(Auton auton)
    {
        auton.addCommand(new CmdRaisePillars(kThirdLevel,0.5,auton.getPillars()));
        auton.addCommand(new CmdMovePillarWheelsTime(2, 0.6, auton.getPillars()));
        auton.addCommand(new CmdLowerSinglePillar(true, 0, 0.7, auton.getPillars()));
        auton.addCommand(new CmdDriveTime(0.5, auton.getDrive()));
        auton.addCommand(new CmdLowerSinglePillar(false, 0, 0.7, auton.getPillars()));
    }
}