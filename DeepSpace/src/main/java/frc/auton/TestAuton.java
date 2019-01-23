package frc.auton;
import frc.auton.commands.*;
public class TestAuton extends Auton
{
    @Override
    protected void addCommands() {
        addCommand(new CmdDriveTime(1));
        addCommand(new CmdDriveReverse(1.3));
        addCommand(new CmdWait(2));
        addCommand(new CmdDriveTime(0.5));
    }
}