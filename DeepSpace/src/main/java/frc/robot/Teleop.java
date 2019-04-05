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

        

        //System.out.print(determineLinedUp() ? "LINED UP\n" : "") ;

        if(operateStick.getStickButtonPressed(Hand.kRight))
        {
            PanelAuton.addCommands(auton);
        }

        if(delayTimer.get() > 0.25 && auton.getLimelight().doesTargetExist() && limelightAngle == 0)
        {
            limelightAngle = -auton.getLimelight().getTargetX();
        }

        if(operateStick.getStickButton(Hand.kLeft))
        {
            ResetElevatorAuton.addCommands(auton);
            endTeleop();
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
                auton.getElevator().setPower(0);
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


        auton.getLimelight().setCamMode(1);
        auton.getLimelight().setLEDMode(1);
        driveTrain.arcadeDrive(-axis1, axis4);

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
