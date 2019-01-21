package frc.auton;
public class TestAuton extends Auton
{
    @Override
    protected void addCommands() {
        addCommand(new TestCommand());
        addCommand(new TestCommand());
        addCommand(new TestCommand());
    }

}