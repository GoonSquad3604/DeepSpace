package frc.auton;

import frc.commands.drive.*;
import frc.commands.special.*;

public class LockOnAuton
{
    public static void addCommands(Auton auton)
    {
        auton.addCommand(new CmdTurnToAngle(auton.getDrive(),auton.getGyro(),auton.getLimelight()));
        //auton.addCommand(new CmdMerge(new CmdDriveTime(2,auton.getDrive()),new CmdTurnToAngle(auton.getDrive(),auton.getGyro(),auton.getLimelight())));
    }
}
//What happens in Niko's basement stays in Niko's basement
//-Tony