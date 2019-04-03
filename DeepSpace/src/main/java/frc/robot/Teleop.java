package frc.robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.GenericHID.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.auton.*;
import frc.auton.cargo.*;
import frc.auton.elevator.*;
import frc.auton.hatch.*;
import frc.auton.pillars.*;
import frc.commands.AutonCommand;
import frc.subsystem.*;
import frc.subsystem.drivetrain.*;
import frc.vision.*;
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
    private DriverStation driveStation;
    private Timer testTime;
    double distance = 0;
    private boolean isSucking = false;
    Sucker sucker;
    private Limelight limelight;

    public Teleop(DriveTrain iDriveTrain, XboxController iDriveStick, XboxController iOperateStick, Auton iAuton, Limelight iLimelight, Sucker iSucker)
    {
        driveStick = iDriveStick;
        operateStick = iOperateStick;
        driveTrain = iDriveTrain;
        auton = iAuton;
        delayTimer = new Timer();
        driveStation = DriverStation.getInstance();
        testTime = new Timer();
        testTime.start();
        sucker = iSucker;

        limelight = iLimelight;

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
        double axis1 = (Math.abs(driveStick.getRawAxis(1)) > 0.1) ? 0.95 * driveStick.getRawAxis(1) : 0;
        double axis4 = (Math.abs(driveStick.getRawAxis(4)) > 0.1) ? 0.95 * driveStick.getRawAxis(4) : 0;

        SmartDashboard.putNumber("Suck Current", sucker.getCurrent());

        //System.out.print(determineLinedUp() ? "LINED UP\n" : "") ;

        if(operateStick.getStickButtonPressed(Hand.kRight))
        {
            if(isSucking)
            {
                sucker.set(0);
                PlacePanelAuton.addCommands(auton);
            }
            isSucking = !isSucking;
        }
        sucker.set(isSucking ? 0.65 : 0);

        if(delayTimer.get() > 0.25 && auton.getLimelight().doesTargetExist() && limelightAngle == 0)
        {
            limelightAngle = -auton.getLimelight().getTargetX();
        }

        if(driveStick.getBumper(Hand.kLeft))
        {
            PlacePanelAuton.addCommands(auton);
            endTeleop();
        }
        else if(false && driveStick.getBumper(Hand.kRight) || (auton.getHatchManipulator().getSensor() && operateStick.getStickButton(Hand.kRight)))
        {
            PickupPanelAuton.addCommands(auton);
            endTeleop();
        }

        if(operateStick.getStickButton(Hand.kLeft))
        {
            ResetElevatorAuton.addCommands(auton);
            endTeleop();
        }

        // if(driveStick.getBumper(Hand.kLeft))
        // {
        //     auton.getLimelight().setCamMode(0);
        //     auton.getLimelight().setLEDMode(0);

        //     if(limelightAngle != 0)
        //     {
        //         if(Math.abs(ypr[0] - limelightAngle) < 2)
        //         {
        //             driveTrain.arcadeDrive(0,0);
        //         }
        //         else if(ypr[0] > limelightAngle)
        //         {
        //             driveTrain.arcadeDrive(0, 0.4);
        //         }
        //         else if(ypr[0] < limelightAngle)
        //         {
        //             driveTrain.arcadeDrive(0, -0.4);
        //         }
        //         else
        //         {
        //             driveTrain.arcadeDrive(0,0);
        //         }
        //     }
        //     else
        //     {
        //         driveTrain.arcadeDrive(0, 0);
        //     }
            
        // }
        // else if(driveStick.getBumper(Hand.kRight))
        // {
        //     if(auton.getSonar().getInches() >= 2)
        //     {
        //         driveTrain.arcadeDrive(0.7, 0);
        //     }
        //     else
        //     {
        //         driveTrain.arcadeDrive(0, 0);
        //     }
        // }
        // else if(driveStick.getBumper(Hand.kRight) && driveStick.getBumper(Hand.kLeft))
        // {
        //     double drv;
        //     double turn;
        //     auton.getLimelight().setCamMode(0);
        //     auton.getLimelight().setLEDMode(0);

        //     if(Math.abs(ypr[0] - limelightAngle) < 2)
        //     {
        //         turn = 0;
        //     }
        //     else if(ypr[0] < limelightAngle)
        //     {
        //         turn = .35;
        //     }
        //     else if(ypr[0] > limelightAngle)
        //     {
        //         turn = -.35;
        //     }
        //     else
        //     {
        //         turn = 0;
        //     }

        //     if(auton.getSonar().getInches() >= 2)
        //     {
        //         drv = 0.7;
        //     }
        //     else
        //     {
        //         drv = 0;
        //     }

        //     driveTrain.arcadeDrive(-drv, turn);
        // }
        // else if(driveStick.getStickButton(Hand.kRight)){
        //     if(testTime.get() >= 1 && testTime.get() <= 2){
        //         distance = (driveTrain.getLeftPosition() + driveTrain.getRightPosition()) / 2;
        //         driveTrain.arcadeDrive(1, 0);
        //     }
        //     else if(testTime.get() < 1 && testTime.get() > 0){
        //         driveTrain.setRightPosition(0);
        //         driveTrain.setLeftPosition(0);
        //         driveTrain.arcadeDrive(1, 0);
        //     }
        //     else{
        //         driveTrain.arcadeDrive(0, 0);
        //     }
        //     System.out.println(distance);
        // }
        else 
        {
            //auton.getLimelight().setStreamMode(1);
            testTime.reset();
            limelightAngle = 0;
            auton.getLimelight().setCamMode(1);
            auton.getLimelight().setLEDMode(1);
            // if(operateStick.getStickButton(Hand.kRight))
            // {
            //     axis1 *= 0.5;
            //     axis4 *= 0.5;
            // }
            driveTrain.arcadeDrive(-axis1, axis4);
            auton.getGyro().setYaw(0, kTimeoutMs);
            delayTimer.reset();
        }

        if(auton.getSize() == 0 && running)
        {
            
            if(driveStick.getPOVCount() != 0 && driveStick.getStartButton())
            {
                switch(driveStick.getPOV())
                {
                    case kDpadUp:
                        PillarsAuton.addCommands(auton, kThirdLevel);
                        endTeleop();
                        break;
                    case kDpadDown:
                        PillarsAuton.addCommands(auton, kSecondLevel);
                        endTeleop();
                        break;
                    default:
                        break;
                }
            }
            else
            {
                auton.getPillars().runManualPillars(driveStick);
            }

            //When the operator presses A and a directional button, place a cargo.
            if(operateStick.getAButton() && operateStick.getPOV() != -1)
            {
                switch(operateStick.getPOV())
                {
                    case kDpadUp:
                        CargoPlaceAuton2.addCommands(auton, kTopRocketCargo);
                        break;
                    case kDpadRight:
                        CargoPlaceAuton2.addCommands(auton, kMiddleRocketCargo);
                        break;
                    case kDpadDown:
                        CargoPlaceAuton2.addCommands(auton, kBottomRocketCargo);
                        break;
                    case kDpadLeft:
                        CargoPlaceAuton2.addCommands(auton, kCargoShip);
                        break;
                }
                endTeleop();
            }
            else if(operateStick.getBButton() && operateStick.getPOV() != -1)
            {
                switch(operateStick.getPOV())
                {
                    case kDpadUp:
                        HatchPlaceAuton2.addCommands(auton, kTopRocketHatch);
                        break;
                    case kDpadRight:
                        HatchPlaceAuton2.addCommands(auton, kMiddleRocketHatch);
                        break;
                    case kDpadDown:
                        HatchPlaceAuton2.addCommands(auton, kBottomRocketHatch);
                        break;
                    case kDpadLeft:
                        HatchPlaceAuton2.addCommands(auton, kHatchFeeder);
                        break;
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
                    auton.getElevator().setPower(.5);
                }
                else if(operateStick.getPOV() == kDpadDown)
                {
                    auton.getElevator().setPower(-.5);
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

            if(operateStick.getTriggerAxis(Hand.kRight) >= 0.6)
            {
                ToggleHatch.addCommands(auton);
                endTeleop();
            }
            else if(operateStick.getStartButton())
            {
                auton.getHatchManipulator().runArticulator(-1);
            }
            else if(operateStick.getBackButton())
            {
                auton.getHatchManipulator().runArticulator(1);
            }
            else
            {
                auton.getHatchManipulator().runArticulator(0);
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
        driveStick.getStartButtonPressed();
        driveStick.getBackButtonPressed();
        driveStick.getBumperPressed(Hand.kLeft);
        driveStick.getBumperPressed(Hand.kRight);
        driveStick.getStickButtonPressed(Hand.kLeft);
        driveStick.getStickButtonPressed(Hand.kRight);

        operateStick.getAButtonPressed();
        operateStick.getBButtonPressed();
        operateStick.getXButtonPressed();
        operateStick.getYButtonPressed();
        operateStick.getStartButtonPressed();
        operateStick.getBackButtonPressed();
        operateStick.getBumperPressed(Hand.kLeft);
        operateStick.getBumperPressed(Hand.kRight);
        operateStick.getStickButtonPressed(Hand.kLeft);
        operateStick.getStickButtonPressed(Hand.kRight);

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

    private boolean determineLinedUp()
    {
        return (limelight.getTargetX() > -2 && limelight.getTargetX() < 2);
    }
}
