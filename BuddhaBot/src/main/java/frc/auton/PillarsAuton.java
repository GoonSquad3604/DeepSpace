package frc.auton;
import static frc.robot.Constants.*;

import frc.commands.subsystem.*;
import frc.commands.drive.*;
public class PillarsAuton
{
    public static void addCommands(Auton auton)
    {
        auton.addCommand(new CmdRaisePillars(kThirdLevel,0.8,auton.getPillars()));
        auton.addCommand(new CmdDriveDistance(12,auton.getDrive()));
        auton.addCommand(new CmdLowerSinglePillar(true,5,0.8,auton.getPillars()));
        auton.addCommand(new CmdMovePillarWheels(10,0.6,auton.getPillars()));
        auton.addCommand(new CmdLowerSinglePillar(false,5,0.8,auton.getPillars()));
        auton.addCommand(new CmdDriveDistance(10,auton.getDrive()));
    }
}