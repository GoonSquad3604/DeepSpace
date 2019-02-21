package frc.auton;

import frc.commands.AutonCommand;
import frc.commands.drive.*;
import frc.commands.special.CmdDistance;
import frc.commands.special.CmdMerge;
import frc.commands.special.CmdMergeOnStatus;
import frc.commands.subsystem.CmdDispenseForTime;
import frc.commands.subsystem.CmdMoveElevator;
import frc.commands.subsystem.CmdRotateHinge;

import static frc.robot.Constants.*;
public class CargoPlaceAuton
{

    public static void addCommands(Auton auton, double elevatorHeight)
    {

        auton.addCommand(new CmdTurnToAngle(auton.getDrive(),auton.getGyro(), auton.getLimelight()));

        auton.addCommand(new CmdMergeOnStatus(0,
            new CmdDriveSonar(10,auton.getSonar(),auton.getDrive()),
            new CmdMoveElevator(elevatorHeight, auton.getElevator())));

        auton.addCommand(new CmdRotateHinge(kHingeOut, 0.5, auton.getCargoManipulator()));
        auton.addCommand(new CmdDispenseForTime( 0.2, auton.getCargoManipulator()));
        
        auton.addCommand(new CmdMerge(
            new CmdRotateHinge(kHingeUp, 0.5, auton.getCargoManipulator()),
            new CmdDriveReverse(0.5, auton.getDrive())));

    }

}