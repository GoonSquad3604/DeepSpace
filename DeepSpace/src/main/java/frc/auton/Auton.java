package frc.auton;

import java.util.LinkedList;
import java.util.Queue;

import com.ctre.phoenix.sensors.PigeonIMU;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.commands.*;
import frc.commands.special.*;
import frc.subsystem.drivetrain.DriveTrain;
import frc.auton.exceptions.TooManyControllersException;
import frc.auton.exceptions.UnsupportedSubsystemException;
import frc.vision.Limelight;

public class Auton
{
    //The queue of commands. Commands are added to it, and they are run in sequence.
    Queue<AutonCommand> autonQueue;
    private boolean initted = false;
    private DriveTrain drive;
    private XboxController driveStick;
    private XboxController operateStick;
    private AutonCommand defaultCommand;
    private PigeonIMU gyro;
    private Limelight limelight;
    
    public DriveTrain getDrive()
    {
        return drive;
    }
    public XboxController getDriveStick()
    {
        return driveStick;
    }
    public XboxController getOperateStick()
    {
        return operateStick;
    }
    public Limelight getLimelight()
    {
        return limelight;
    }
    public PigeonIMU getGyro()
    {
        return gyro;
    }
    public Auton(Object... subsystems)
    {
        autonQueue = new LinkedList<AutonCommand>();
         //You can put subsystems into the constructor. This handles the subsystems added.
        for(int i=0; i<subsystems.length; i++)
        {
            loadSubsystem(subsystems[i]);
        }
        defaultCommand = new CmdTeleop(drive, driveStick, operateStick, this);
        this.initted = false;
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
            else if(subsystem instanceof PigeonIMU)
            {
                System.out.println("LOADED GYRO");
                gyro = (PigeonIMU)subsystem;
            }
            else if(subsystem instanceof Limelight)
            {
                limelight = (Limelight)subsystem;
            }
            else if(subsystem != null)
            {
                throw new UnsupportedSubsystemException(subsystem);  //HELP! I DON'T KNOW WHAT SUBSYSTEM THIS IS!!!
            }
            else
            {
                throw new NullPointerException("NULL SUBSYSTEM");
            }
    }

    //Size of auton queue.
    public int getSize()
    {
        return autonQueue.size();
    }

    //Where commands are defined. Exists so that commands can be easily located.
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
        if(initted && (autonQueue.isEmpty() || autonQueue.peek() == null || autonQueue.size() == 0))
        {
            if(defaultCommand instanceof CmdTeleop)
            {
                CmdTeleop teleop = (CmdTeleop) defaultCommand;
                if(!teleop.getRunning())
                {
                    defaultCommand.init();
                }
            }
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
        if(autonQueue.peek() != null)
        {
            autonQueue.peek().init();
        }
    }
    @Override
    public String toString()
    {
        String s = this.getClass().getName() + " WITH SIZE " + getSize() + "\n";
        for(int i=0; i<autonQueue.size();i++)
        {
            s += autonQueue.getClass().getName() + "\n";
        }
        return s;
    }
}