package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import frc.auton.*;
import frc.commands.AutonCommand;
import frc.subsystem.drivetrain.*;
import static frc.robot.Constants.*;

public class Teleop implements AutonCommand
{

    //Runs when there is no command. Does Teleoperated Stuffs.
    private DriveTrain drive;
    private XboxController driveStick;
    private XboxController operateStick;
    private Auton auton;
    private boolean running;
    private double limelightAngle;
    private double[] ypr;
    
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
        auton.getGyro().getYawPitchRoll(ypr);
        System.out.println("WE ARE IN TELEOP");
        double axis1 = (Math.abs(driveStick.getRawAxis(1)) > 0.1) ? driveStick.getRawAxis(1) : 0;
        double axis4 = (Math.abs(driveStick.getRawAxis(4)) > 0.1) ? driveStick.getRawAxis(4) : 0;
        if(driveStick.getAButton())
        {
            auton.getLimelight().setCamMode(0);
            if(Math.abs(ypr[0] - limelightAngle) < 2)
            {
                drive.arcadeDrive(0,0);
            }
            else if(ypr[0] < limelightAngle)
            {
                drive.arcadeDrive(0,0.35);
            }
            else if(ypr[0] > limelightAngle)
            {
                drive.arcadeDrive(0,0);
            }
            else
            {
                drive.arcadeDrive(0,0);
            }
        }
        else
        {
            drive.arcadeDrive(-axis1,axis4);
            limelightAngle = auton.getLimelight().getTargetX();
            auton.getGyro().setYaw(0,10);
            auton.getLimelight().setCamMode(1);
        }
        if(auton.getSize() == 0 && running)
        {
            //When the operator presses A and a directional button, place a cargo.
            if(operateStick.getAButton() && operateStick.getPOV() != kDpadNone)
            {
                if(operateStick.getPOV() == kDpadUp)
                {
                    CargoPlaceAuton.addCommands(auton,kTopRocketCargo);
                }
                else if(operateStick.getPOV() == kDpadRight)
                {
                    CargoPlaceAuton.addCommands(auton,kMiddleRocketCargo);
                }
                else if(operateStick.getPOV() == kDpadDown)
                {
                    CargoPlaceAuton.addCommands(auton,kBottomRocketCargo);
                }
                else if(operateStick.getPOV() == kDpadLeft)
                {
                    CargoPlaceAuton.addCommands(auton,kCargoShip);
                }
                endTeleop();
            }
            if(operateStick.getYButton())
            {
                if(operateStick.getPOV() == 0)
                {
                    auton.getCargoManipulator().runHinge(1);
                }
                else if(operateStick.getPOV() == 180)
                {
                    auton.getCargoManipulator().runHinge(-1);
                }
                else
                {
                    auton.getCargoManipulator().runHinge(0);
                }
            }
            else
            {
                auton.getCargoManipulator().runHinge(0);
            }
            if(operateStick.getBumper(Hand.kLeft))
            {
                auton.getCargoManipulator().runDispense();
            }
            else if(operateStick.getBumper(Hand.kRight))
            {
                auton.getCargoManipulator().runIntake();
            }
            else
            {
                auton.getCargoManipulator().stop();
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
        //drive.setLeftPosition(0);
        //drive.setRightPosition(0);
    }
    private void endTeleop()
    {   
        this.running = false;
        this.end();
    }
}
