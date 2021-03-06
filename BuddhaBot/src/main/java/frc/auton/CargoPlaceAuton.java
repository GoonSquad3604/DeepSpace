package frc.auton;

import frc.commands.AutonCommand;
import frc.commands.drive.*;
import frc.commands.special.CmdDistance;
import frc.commands.special.CmdMergeOnStatus;
import frc.commands.special.CmdWait;
import frc.commands.subsystem.CmdChangePipeline;
import frc.commands.subsystem.CmdDispenseForTime;
import frc.commands.subsystem.CmdMoveElevator;

public class CargoPlaceAuton
{

    public static void addCommands(Auton auton)
    {
        auton.addCommand(new CmdChangePipeline(0,auton.getLimelight()));
        auton.addCommand(new CmdWait(0.5));
        auton.addCommand(new CmdTurnToAngle(auton.getDrive(),auton.getGyro(), auton.getLimelight()));
        auton.addCommand(new CmdMergeOnStatus(0,
            new CmdDriveTime(1.5,auton.getDrive())));
        auton.addCommand(new CmdTurnToAngle(auton.getDrive(),auton.getGyro(), auton.getLimelight()));
        auton.addCommand(new CmdDriveSonar(10,auton.getSonar(),auton.getDrive()));
        //auton.addCommand(new CmdDriveReverse(0.25, auton.getDrive()));
    }

}