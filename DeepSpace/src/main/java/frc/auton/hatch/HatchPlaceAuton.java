package frc.auton.hatch;

import frc.commands.drive.*;
import frc.commands.special.CmdDistance;
import frc.commands.special.CmdMergeOnStatus;
import frc.auton.Auton;

public class HatchPlaceAuton
{
    //A skeleton for moving to the hatch panel and placing it.
    @Deprecated
    public static void addCommands(Auton auton)
    {
        addCommands(auton,100);
    }
    
    public static void addCommands(Auton auton, double height)
    {
        auton.addCommand(new CmdTurnToAngle(auton.getDrive(),auton.getGyro(), auton.getLimelight()));
        auton.addCommand(new CmdMergeOnStatus(0,
            new CmdDriveToArea(0.6,1,auton.getLimelight(),auton.getDrive())
            /*new CmdMoveElevator(0, auton.getElevator())*/));
        //auton.addCommand(new CmdToggleLotus(auton.getLotus()));
        auton.addCommand(new CmdDriveReverse(1.5, auton.getDrive()));
    }
    
}