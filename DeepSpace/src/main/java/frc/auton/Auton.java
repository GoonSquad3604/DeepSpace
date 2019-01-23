package frc.auton;

import java.util.LinkedList;
import java.util.Queue;
import frc.auton.commands.*;

public abstract class Auton
{
    //The queue of commands. Commands are added to it, and they are run in sequence.
    Queue<AutonCommand> autonQueue;
    private boolean initted = false;
    public Auton()
    {
        autonQueue = new LinkedList<AutonCommand>();
        addCommands();
    }
    //Size of auton queue.
    public int size()
    {
        return autonQueue.size();
    }
    //Where commands are defined. Exists so that commands can be easily located.
    protected abstract void addCommands();
    //Runs the auton's latest command. Will be called in autonomousPeriodic.
    public void runAuton()
    {
        if(!initted)
        {
            initAuton();
            initted = true; //IMPORTANT
        }
        AutonCommand aCommand = autonQueue.peek();
        if(aCommand != null && aCommand.isFinished())
        {
            autonQueue.remove(aCommand);
            if(autonQueue.peek() != null)
            {
                autonQueue.peek().init();
            }
        }
        else if(aCommand != null)
        {
            aCommand.runTask();
        }
    }
    //Returns true if the auton is complete.
    public boolean isFinished()
    {
        if(autonQueue.isEmpty() || autonQueue.peek() == null || autonQueue.size() == 0)
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
    //Called once at the beginning of auton.
    public void initAuton()
    {
        autonQueue.peek().init();
    }
    @Override
    public String toString()
    {
        String s = this.getClass().getName() + " WITH SIZE " + size() + "\n";
        for(int i=0; i<autonQueue.size();i++)
        {
            s += autonQueue.getClass().getName() + "\n";
        }
        return s;
    }
}