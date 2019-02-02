package frc.auton;

import frc.commands.drive.*;
import frc.commands.special.CmdDistance;
import frc.commands.special.CmdMergeOnStatus;
import frc.commands.subsystem.CmdMoveElevator;
import frc.commands.subsystem.CmdToggleBeak;

public class HatchPlaceAuton
{
    //A skeleton for moving to the hatch panel and placing it.
    public static void addCommands(Auton auton)
    {
        auton.addCommand(new CmdTurnToAngle(auton.getDrive(),auton.getGyro(), auton.getLimelight()));
        auton.addCommand(new CmdMergeOnStatus(0,
            new CmdDistance(auton.getLimelight()),
            new CmdMoveElevator(0, auton.getElevator()
        )));
        auton.addCommand(new CmdToggleBeak(auton.getBeak()));
        auton.addCommand(new CmdDriveReverse(0.5, auton.getDrive()));

    }
}