package frc.commands.drive;

import edu.wpi.first.wpilibj.XboxController;
import frc.auton.Auton;
import frc.commands.AutonCommand;
import frc.subsystem.drivetrain.DriveTrain;

public class CmdManualDrive implements AutonCommand
{

    //This command allows the driver to remain in control of the drive train, even when the
    //other subsystems are doing other things.
    private DriveTrain drive;
    private XboxController driveStick;
    private Auton auton;
    private double[] ypr;
    private double limelightAngle;
    
    public CmdManualDrive(DriveTrain iDrive, XboxController iDriveStick, Auton iAuton)
    {
        drive = iDrive;
        driveStick = iDriveStick;
        auton = iAuton;
    }
    @Override
    public boolean isFinished() 
    {
        //Code is highly similar to teleop driving auton. The operator may not operate while this is running.
        auton.getGyro().getYawPitchRoll(ypr);
        System.out.println("WE ARE NOT IN TELEOP!!\nTHE GINGER MAY DRIVE STILL.\nVANILLA ICE MAN MAY NOT OPERATE.\nROBOT IS NOT ON CART. TONY THE TECHNICIAN MAY NOT TOUCH\nCPTN DISAPPOINTMENT IS STILL A DISAPPOINTMENT!\n=====================================");
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
            auton.getLimelight().setCamMode(1);
            drive.arcadeDrive(-axis1,axis4);
            limelightAngle = auton.getLimelight().getTargetX();
            auton.getGyro().setYaw(0,10);
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