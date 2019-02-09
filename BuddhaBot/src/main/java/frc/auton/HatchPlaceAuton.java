package frc.auton;

import frc.commands.drive.*;
import frc.commands.special.CmdDistance;
import frc.commands.special.CmdMergeOnStatus;
import frc.commands.special.CmdWait;
import frc.commands.subsystem.CmdChangePipeline;
import frc.commands.subsystem.CmdMoveElevator;
import frc.commands.subsystem.CmdToggleLotus;

public class HatchPlaceAuton
{
    //A skeleton for moving to the hatch panel and placing it.
    public static void addCommands(Auton auton)
    {
        auton.addCommand(new CmdChangePipeline(0,auton.getLimelight()));
        auton.addCommand(new CmdWait(0.5));
        auton.addCommand(new CmdTurnToAngle(auton.getDrive(),auton.getGyro(), auton.getLimelight()));
        auton.addCommand(new CmdMergeOnStatus(0,
            new CmdDriveTime(1.5,auton.getDrive())
            /*new CmdMoveElevator(0, auton.getElevator())*/));
        //auton.addCommand(new CmdToggleLotus(auton.getLotus()));
        //auton.addCommand(new CmdDriveTime(1,auton.getDrive()));
        auton.addCommand(new CmdChangePipeline(1,auton.getLimelight()));
        auton.addCommand(new CmdWait(0.1));
        auton.addCommand(new CmdTurnToAngle(auton.getDrive(),auton.getGyro(), auton.getLimelight()));
        auton.addCommand(new CmdDriveSonar(10,auton.getSonar(),auton.getDrive()));
        //auton.addCommand(new CmdDriveReverse(0.25, auton.getDrive()));
    }
    
}