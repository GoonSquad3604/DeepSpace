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
    private double[] ypr = new double[3];
    private TeleopParts teleopParts;
    
    public Teleop(DriveTrain drive, XboxController driveStick, XboxController operateStick, Auton auton)
    {
        this.driveStick = driveStick;
        this.operateStick = operateStick;
        this.drive = drive;
        this.auton = auton;
        teleopParts = new TeleopParts(auton,driveStick,operateStick);
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
        System.out.println(auton.getHatchManipulator().getLocation());
        double axis1 = (Math.abs(driveStick.getRawAxis(1)) > 0.1) ? driveStick.getRawAxis(1) : 0;
        double axis4 = (Math.abs(driveStick.getRawAxis(4)) > 0.1) ? driveStick.getRawAxis(4) : 0;
        if(driveStick.getBumper(Hand.kLeft))
        {
            auton.getLimelight().setCamMode(0);
            auton.getLimelight().setLEDMode(0);
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
                drive.arcadeDrive(0,-0.35);
            }
            else
            {
                drive.arcadeDrive(0,0);
            }
        }
        else if(driveStick.getBumper(Hand.kRight))
        {
            if(auton.getSonar().getInches() >= 2)
            {
                drive.arcadeDrive(0.7,0);
            }
            else
            {
                drive.arcadeDrive(0,0);
            }
        }
        else if(driveStick.getBumper(Hand.kRight) && driveStick.getBumper(Hand.kLeft))
        {
            double drv;
            double turn;
            auton.getLimelight().setCamMode(0);
            auton.getLimelight().setLEDMode(0);
            if(Math.abs(ypr[0] - limelightAngle) < 2)
            {
                turn = 0;
            }
            else if(ypr[0] < limelightAngle)
            {
                turn = .35;
            }
            else if(ypr[0] > limelightAngle)
            {
                turn = -.35;
            }
            else
            {
                turn = 0;
            }
            if(auton.getSonar().getInches() >= 2)
            {
                drv = 0.7;
            }
            else
            {
                drv = 0;
            }
            drive.arcadeDrive(-drv,turn);
        }
        else
        {
            auton.getLimelight().setCamMode(1);
            auton.getLimelight().setLEDMode(1);
            drive.arcadeDrive(-axis1,axis4);
            limelightAngle = auton.getLimelight().getTargetX();
            auton.getGyro().setYaw(0,10);
        }
        if(auton.getSize() == 0 && running)
        {
            //When the operator presses A and a directional button, place a cargo.
            if(operateStick.getAButton() && operateStick.getPOV() != kDpadNone)
            {
                if(operateStick.getPOV() == kDpadUp)
                {
                    CargoPlaceAuton2.addCommands(auton,kTopRocketCargo,teleopParts);
                }
                else if(operateStick.getPOV() == kDpadRight)
                {
                    CargoPlaceAuton2.addCommands(auton,kMiddleRocketCargo,teleopParts);
                }
                else if(operateStick.getPOV() == kDpadDown)
                {
                    CargoPlaceAuton2.addCommands(auton,kBottomRocketCargo,teleopParts);
                }
                else if(operateStick.getPOV() == kDpadLeft)
                {
                    CargoPlaceAuton2.addCommands(auton,kCargoShip,teleopParts);
                }
                endTeleop();
            }
            else if(operateStick.getBButton() && operateStick.getPOV() != kDpadNone)
            {
                if(operateStick.getPOV() == kDpadUp)
                {
                    CargoPlaceAuton2.addCommands(auton,kTopRocketHatch,teleopParts);
                }
                else if(operateStick.getPOV() == kDpadRight)
                {
                    CargoPlaceAuton2.addCommands(auton,kMiddleRocketHatch,teleopParts);
                }
                else if(operateStick.getPOV() == kDpadDown)
                {
                    CargoPlaceAuton2.addCommands(auton,kBottomRocketHatch,teleopParts);
                }
                else if(operateStick.getPOV() == kDpadLeft)
                {
                    CargoPlaceAuton2.addCommands(auton,kHatchShip,teleopParts);
                }
                endTeleop();
            }
            else
            {
                auton.getPillars().runOldChadCode(driveStick);

                if(operateStick.getPOV() == kDpadLeft)
                {
                    auton.getHatchManipulator().runHatch(-1);
                }
                else if(operateStick.getPOV() == kDpadRight)
                {
                    auton.getHatchManipulator().runHatch(1);
                }
                else(operateStick.getPOV() == kDpadNone)
                {
                    auton.getHatchManipulator().runHatch(0);
                }

            }
            /*
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
            }*/
            teleopParts.permitHingeMovement();
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
