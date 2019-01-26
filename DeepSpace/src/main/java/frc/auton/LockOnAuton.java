package frc.auton;

import frc.commands.drive.CmdTurnToAngle;

public class LockOnAuton
{
    public static void addCommands(Auton auton)
    {
        auton.addCommand(new CmdTurnToAngle(auton.getDrive(),auton.getGyro(),auton.getLimelight()));
    }
}