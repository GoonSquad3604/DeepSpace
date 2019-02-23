package frc.robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Watchdog;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import frc.auton.*;
import frc.commands.AutonCommand;
import frc.subsystem.drivetrain.*;
import static frc.robot.Constants.*;

public class Teleop implements AutonCommand
{

    //Runs when there is no command. Does Teleoperated Stuffs.
    private DriveTrain driveTrain;
    private XboxController driveStick;
    private XboxController operateStick;
    private Auton auton;
    private boolean running;
    private double limelightAngle;
    private double[] ypr = new double[3];
    private Timer delayTimer;

    public Teleop(DriveTrain iDriveTrain, XboxController iDriveStick, XboxController iOperateStick, Auton iAuton)
    {
        driveStick = iDriveStick;
        operateStick = iOperateStick;
        driveTrain = iDriveTrain;
        auton = iAuton;
        delayTimer = new Timer();
    }
    
    @Override
    public boolean isFinished() 
    {
        return false;
    }

    @Override
    public void runTask() 
    {
        
        auton.getOperateStick().setRumble(RumbleType.kLeftRumble,0);
        auton.getOperateStick().setRumble(RumbleType.kRightRumble,0);
        auton.getDriveStick().setRumble(RumbleType.kLeftRumble,0);
        auton.getDriveStick().setRumble(RumbleType.kRightRumble,0);
        auton.getGyro().getYawPitchRoll(ypr);
        //System.out.println(ypr[0] + "::" + limelightAngle);
        //System.out.println(auton.getHatchManipulator().getLocation());
        double axis1 = (Math.abs(driveStick.getRawAxis(1)) > 0.1) ? driveStick.getRawAxis(1) : 0;
        double axis4 = (Math.abs(driveStick.getRawAxis(4)) > 0.1) ? driveStick.getRawAxis(4) : 0;

        if(delayTimer.get() > 0.25 && auton.getLimelight().doesTargetExist() && limelightAngle == 0)
        {
            limelightAngle = -auton.getLimelight().getTargetX();
        }
        if(driveStick.getBumper(Hand.kLeft))
        {
            auton.getLimelight().setCamMode(0);
            auton.getLimelight().setLEDMode(0);
            if(Math.abs(Math.abs(ypr[0]) - Math.abs(limelightAngle)) < 2)
            {
                driveTrain.arcadeDrive(0,0);
            }
            else if(ypr[0] > limelightAngle)
            {
                driveTrain.arcadeDrive(0,0.4);
            }
            else if(ypr[0] < limelightAngle)
            {
                driveTrain.arcadeDrive(0,-0.4);
            }
            else
            {
                driveTrain.arcadeDrive(0,0);
            }
        }
        else if(driveStick.getBumper(Hand.kRight))
        {
            if(auton.getSonar().getInches() >= 2)
            {
                driveTrain.arcadeDrive(0.7,0);
            }
            else
            {
                driveTrain.arcadeDrive(0,0);
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

            driveTrain.arcadeDrive(-drv,turn);
        }
        else
        {
            limelightAngle = 0;
            auton.getLimelight().setCamMode(1);
            auton.getLimelight().setLEDMode(1);
            driveTrain.arcadeDrive(-axis1,axis4);
            auton.getGyro().setYaw(0, kTimeoutMs);
            delayTimer.reset();
        }

        if(auton.getSize() == 0 && running)
        {
            auton.getPillars().runOldChadCode(driveStick);
            if(driveStick.getStartButton() && driveStick.getBackButton())
            {
                PillarsAuton.addCommands(auton);
                endTeleop();
            }
            else if(driveStick.getStartButton())
            {
                auton.getPillars().reset();
            }
            if(operateStick.getStartButton())
            {
                auton.getElevator().setHeight(0);
            }
            //When the operator presses A and a directional button, place a cargo.
            if(operateStick.getAButton() && operateStick.getPOV() != kDpadNone)
            {
                if(operateStick.getPOV() == kDpadUp)
                {
                    CargoPlaceAuton2.addCommands(auton, kTopRocketCargo);
                }
                else if(operateStick.getPOV() == kDpadRight)
                {
                    CargoPlaceAuton2.addCommands(auton, kMiddleRocketCargo);
                }
                else if(operateStick.getPOV() == kDpadDown)
                {
                    CargoPlaceAuton2.addCommands(auton, kBottomRocketCargo);
                }
                else if(operateStick.getPOV() == kDpadLeft)
                {
                    CargoPlaceAuton2.addCommands(auton, kCargoShip);
                }
                endTeleop();
            }
            else if(operateStick.getBButton() && operateStick.getPOV() != kDpadNone)
            {
                if(operateStick.getPOV() == kDpadUp)
                {
                    CargoPlaceAuton2.addCommands(auton, kTopRocketHatch);
                }
                else if(operateStick.getPOV() == kDpadRight)
                {
                    CargoPlaceAuton2.addCommands(auton, kMiddleRocketHatch);
                }
                else if(operateStick.getPOV() == kDpadDown)
                {
                    CargoPlaceAuton2.addCommands(auton, kBottomRocketHatch);
                }
                else if(operateStick.getPOV() == kDpadLeft)
                {
                    CargoPlaceAuton2.addCommands(auton, kHatchFeeder);
                }
                endTeleop();
            }
            else if(operateStick.getTriggerAxis(Hand.kLeft) >= 0.6)
            {
                if(operateStick.getPOV() == kDpadNone)
                {
                    auton.getElevator().setPower(0);
                }
                else if(operateStick.getPOV() == kDpadUp)
                {
                    auton.getElevator().setPower(0.75);
                }
                else if(operateStick.getPOV() == kDpadDown)
                {
                    auton.getElevator().setPower(-1);
                }
            }
            else
            {
               // auton.getPillars().runOldChadCode(driveStick);
                auton.getElevator().setPower(0);

                /*
                if(operateStick.getPOV() == kDpadLeft)
                {
                    auton.getHatchManipulator().runHatch(-1);
                }
                else if(operateStick.getPOV() == kDpadRight)
                {
                    auton.getHatchManipulator().runHatch(1);
                }
                else if(operateStick.getPOV() == kDpadNone)
                {
                    auton.getHatchManipulator().runHatch(0);
                }*/
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
        delayTimer.reset();
        delayTimer.start();
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
