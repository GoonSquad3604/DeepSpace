package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import frc.auton.*;
import frc.commands.AutonCommand;
import frc.subsystem.drivetrain.*;

public class Teleop implements AutonCommand
{

    //Runs when there is no command. Does Teleoperated Stuffs.
    private DriveTrain drive;
    private XboxController driveStick;
    private XboxController operateStick;
    private Auton auton;
    private boolean running;
    
    public Teleop(DriveTrain drive, XboxController driveStick, XboxController operateStick, Auton auton)
    {
        this.driveStick = driveStick;
        this.operateStick = operateStick;
        this.drive = drive;
        this.auton = auton;
    }
    
    @Override
    public boolean isFinished() 
    {
        return false;
    }

    @Override
    public void runTask() 
    {
        drive.arcadeDrive(-driveStick.getRawAxis(1),driveStick.getRawAxis(4));
        if(auton.getSize() == 0 && running)
        {
            //Run a command when A is pressed.
           if(driveStick.getAButtonPressed())
            {
                HatchPlaceAuton.addCommands(this.auton);
                this.running = false;
                this.end();
            }
            //Run a command when B is pressed.
            if(driveStick.getBButtonPressed())
            {
                TestAuton.addCommands(this.auton);
                this.running = false;
                this.end();
            }
            
            if(operateStick.getStartButton())
            {
                PillarsAuton.addCommands(this.auton);
                this.running = false;
                this.end();   
            }
            /*
            if(operateStick.getPOV() == 0)
            {
                auton.getPillars().setFrontPillar(0.5);
                auton.getPillars().setRearPillar(0.5);
            }
            else if(operateStick.getPOV() == 180 && auton.getPillars().getHeight()>0)
            {
                auton.getPillars().setFrontPillar(-0.5);
                auton.getPillars().setRearPillar(-0.5);
            }
            else
            {
                auton.getPillars().setPillars(0);
            }*/
            //Run a command when the left bumper is pressed.
            if(driveStick.getBumper(Hand.kLeft))
            {
                
                LockOnAuton.addCommands(this.auton);
                this.running = false;
                this.end();
                auton.initAuton();
                System.out.println("added to queue! iygutdyetrdytrduurrr");
            }

        }
    }

    @Override
    public double getStatus() 
    {
        return 0;
    }

    @Override
    public void init() 
    {
        this.running = true;
        /*ALL BUTTONS that run auton commands MUST be checked here.
        Without this, Autons may start from accidental button pushes!*/
        driveStick.getAButtonPressed();
        driveStick.getBButtonPressed();
        driveStick.getXButtonPressed();
        driveStick.getYButtonPressed();
        operateStick.getStartButtonPressed();
    }
    
    public boolean getRunning()
    {
        return running;
    }
    
    @Override
    public void end()
    {
        drive.setLeftPosition(0);
        drive.setRightPosition(0);
    }
}
