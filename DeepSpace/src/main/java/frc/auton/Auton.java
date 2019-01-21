package frc.auton;

import java.util.LinkedList;
import java.util.Queue;

public abstract class Auton
{
    //The queue of commands. Commands are added to it, and they are run in sequence.
    Queue<AutonCommand> autonQueue;
    public Auton()
    {
        autonQueue = new LinkedList<AutonCommand>();
        addCommands();
    }
    //Where commands are defined. Exists so that commands can be easily located.
    protected abstract void addCommands();
    //Runs the auton's latest command. Will be called in autonomousPeriodic.
    public void runAuton()
    {
        AutonCommand aCommand = autonQueue.peek();
        if(aCommand.isFinished())
        {
            autonQueue.remove();
        }
        else
        {
            aCommand.runTask();
        }
    }
    //Returns true if the auton is complete.
    public boolean isFinished()
    {
        if(autonQueue.isEmpty())
        {
            return true;
        }
        return false;
    }
    //Yes
    protected void addCommand(AutonCommand command)
    {
        autonQueue.add(command);
    }
}