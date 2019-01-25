package frc.auton;

import java.util.LinkedList;
import java.util.Queue;

import edu.wpi.first.wpilibj.XboxController;
import frc.auton.commands.*;
import frc.auton.exceptions.TooManyControllersException;
import frc.auton.exceptions.UnsupportedSubsystemException;
import frc.subsystem.DriveTrain;
//
public abstract class Auton
{
    //The queue of commands. Commands are added to it, and they are run in sequence.
    Queue<AutonCommand> autonQueue;
    private boolean initted = false;
    protected DriveTrain drive;
    protected XboxController driveStick;
    protected XboxController operateStick;
    private AutonCommand defaultCommand;
    public Auton(Object... subsystems)
    {
        autonQueue = new LinkedList<AutonCommand>();
        //You can put subsystems into the constructor. This handles the subsystems added.
        for(int i=0; i<subsystems.length; i++)
        {
            loadSubsystem(subsystems[i]);
        }
        //Calls the addCommands method in the extended file.
        addCommands();
 
        defaultCommand = new CmdTeleop(drive,driveStick,operateStick);
    }

    //Loads in subsystems.
    private void loadSubsystem(Object subsystem)
    {
        if(subsystem instanceof DriveTrain)
            {
                drive = (DriveTrain)subsystem;  //It's a drivetrain, so make it the drivetrain.
            }
            else if(subsystem instanceof XboxController)
            {
                if(driveStick == null)
                {
                    driveStick = (XboxController)subsystem; //First stick = Driver
                }
                else if(operateStick == null)
                {
                    operateStick = (XboxController)subsystem;   //Second stick = Operator
                }
                else
                {
                    throw new TooManyControllersException();    //Uh oh, there's too many XboxControllers!
                }
            }
            else
            {
                throw new UnsupportedSubsystemException(subsystem);  //HELP! I DON'T KNOW WHAT SUBSYSTEM THIS IS!!!
            }
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
            aCommand.end();
            autonQueue.remove(aCommand);
            if(autonQueue.peek() != null)
            {
                autonQueue.peek().init();
            }
        }
        else if(aCommand != null)
        {
            aCommand.runTask();
            if(driveStick.getXButton()) //Stops queue when X is pressed.
            {
                aCommand.end();
                autonQueue.clear();
            }
        }
    }
    public void runTeleop()
    {
        defaultCommand.runTask();
    }
    //Returns true if the auton is complete.
    public boolean isFinished()
    {
        if(autonQueue.isEmpty() || autonQueue.peek() == null || autonQueue.size() == 0)
        {
            defaultCommand.init();
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