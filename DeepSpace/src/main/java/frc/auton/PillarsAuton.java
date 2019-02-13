package frc.auton;
import static frc.robot.Constants.*;

import frc.commands.subsystem.*;
import frc.commands.drive.*;
import frc.commands.special.*;
public class PillarsAuton
{
    public static void addCommands(Auton auton)
    {
        auton.addCommand(new CmdRaisePillars(kThirdLevel,0.5,auton.getPillars()));
    }
}