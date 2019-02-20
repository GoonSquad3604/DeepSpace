package frc.auton;

import frc.commands.drive.CmdManualDrive;
import frc.commands.special.CmdMerge;
import frc.commands.subsystem.cargo.CmdDispenseForTime;
import frc.robot.TeleopParts;
import frc.commands.subsystem.CmdMoveElevator;
public class CargoPlaceAuton2
{

    public static void addCommands(Auton auton, double elevatorHeight, TeleopParts teleopParts)
    {
        auton.addCommand(new CmdMerge(
            new CmdManualDrive(auton.getDrive(),auton.getDriveStick(),auton.getOperateStick(),auton,teleopParts),
            new CmdMoveElevator(elevatorHeight, auton.getElevator())));
    }

}