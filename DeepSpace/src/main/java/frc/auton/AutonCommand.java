package frc.auton;
public interface AutonCommand
{
    //Will return true when the command has completed.
    boolean isFinished();
    //Will be called in a loop when this command is being run. Will not be called when this command is not being run (I hope).
    void runTask();
    //Returns an int. May be useful at times where you need to get feedback.
    double getStatus();
}