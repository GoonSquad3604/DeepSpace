package frc.auton.commands;
//
public interface AutonCommand
{
    //Will return true when the command has completed.
    boolean isFinished();
    //Will be called in a loop when this command is being run. Will not be called when this command is not being run (I hope).
    void runTask();
    //Returns an int. May be useful at times where you need to get feedback.
    double getStatus();
    //Called when this command begins
    void init();
    //finalizes things. Is blank by default.
    default void end()
    {
        ;
    }
}