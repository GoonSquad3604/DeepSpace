package frc.auton;

import frc.commands.AutonCommand;
import frc.commands.drive.*;
import frc.commands.special.CmdDistance;
import frc.commands.special.CmdMergeOnStatus;
import frc.commands.subsystem.CmdDispenseForTime;
import frc.commands.subsystem.CmdMoveElevator;

public class CargoPlaceAuton
{

    public static void addCommands(Auton auton)
    {

        auton.addCommand(new CmdTurnToAngle(auton.getDrive(),auton.getGyro(), auton.getLimelight()));
        auton.addCommand(new CmdMergeOnStatus(0,
            new CmdDistance(auton.getLimelight()),
            new CmdMoveElevator(0, auton.getElevator())));
        auton.addCommand(new CmdDispenseForTime( 0.2, auton.getCargoManipulator()));
        auton.addCommand(new CmdDriveReverse(0.5, auton.getDrive()));

    }

}