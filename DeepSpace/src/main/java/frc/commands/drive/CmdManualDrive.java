package frc.commands.drive;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import frc.auton.Auton;
import frc.commands.AutonCommand;
import frc.subsystem.drivetrain.DriveTrain;
import static frc.robot.Constants.*;

public class CmdManualDrive implements AutonCommand
{

    //This command allows the driver to remain in control of the drive train, even when the
    //other subsystems are doing other things.
    private DriveTrain drive;
    private XboxController driveStick;
    protected XboxController operateStick;
    private Auton auton;
    private double[] ypr = new double[3];
    private double limelightAngle;
    private boolean operatorAllowed;
    /**
     * Allows you to manually drive the robot during a command.
     * @param iDrive The DriveTrain
     * @param iDriveStick the driver's controller
     * @param iOperateStick the operator's controller
     * @param iAuton the autonomous queue object
     */
    public CmdManualDrive(DriveTrain iDrive, XboxController iDriveStick, XboxController iOperateStick, Auton iAuton)
    {
        this(iDrive,iDriveStick,iOperateStick,iAuton,true);
    }

    /**
     * Allows you to manually drive the robot during a command.
     * @param iDrive The DriveTrain
     * @param iDriveStick the driver's controller
     * @param iOperateStick the operator's controller
     * @param iAuton the autonomous queue object
     * @param iOperatorAllowed whether or not the operator may operate during the drive
     */
    public CmdManualDrive(DriveTrain iDrive, XboxController iDriveStick, XboxController iOperateStick, Auton iAuton, boolean iOperatorAllowed)
    {
        drive = iDrive;
        driveStick = iDriveStick;
        operateStick = iOperateStick;
        auton = iAuton;
        operatorAllowed = iOperatorAllowed;
    }

    @Override
    public boolean isFinished() 
    {
    
        if(operatorAllowed)
        {
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

            if(operateStick.getBumperReleased(Hand.kLeft))
            {
                auton.getCargoManipulator().run(0);
            }
            else if(operateStick.getBumper(Hand.kLeft))
            {
                auton.getCargoManipulator().run(-1);
            }
            else if(operateStick.getBumper(Hand.kRight))
            {
                auton.getCargoManipulator().run(1);
            }
            else if(operateStick.getBumperReleased(Hand.kRight))
            {
                auton.getCargoManipulator().run(0.2);
            }

        }    
        

        //Code is highly similar to teleop driving auton. The operator may not operate while this is running.
        auton.getGyro().getYawPitchRoll(ypr);
        double axis1 = (Math.abs(driveStick.getRawAxis(1)) > 0.1) ? driveStick.getRawAxis(1) : 0;
        double axis4 = (Math.abs(driveStick.getRawAxis(4)) > 0.1) ? driveStick.getRawAxis(4) : 0;
        if(driveStick.getAButton())
        {

            auton.getLimelight().setLEDMode(0);
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
                drive.arcadeDrive(0,-0.35);
            }
            else
            {
                drive.arcadeDrive(0,0);
            }
        }
        else
        {
            auton.getLimelight().setCamMode(1);
            auton.getLimelight().setLEDMode(1);
            drive.arcadeDrive(-axis1,axis4);
            limelightAngle = auton.getLimelight().getTargetX();
            //auton.getGyro().setYaw(0,10);
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

        if(!auton.getIsHatchCommand())
        {
            if(operateStick.getStartButton())
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

        if(!auton.getIsElevatorCommand())
        {
            if(operateStick.getTriggerAxis(Hand.kLeft) >= 0.6)
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
        }


        
        return true;
    }

    @Override
    public void runTask() 
    {

    }

    @Override
    public double getStatus() 
    {
        return 0;
    }

    @Override
    public void init() 
    {

    }

}