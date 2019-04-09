/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.sensors.PigeonIMU;
import com.revrobotics.CANSparkMax.IdleMode;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.auton.*;
import frc.subsystem.*;
import frc.subsystem.drivetrain.*;
import frc.vision.Limelight;
import frc.vision.Sonar;

import static frc.robot.Constants.*;

public class Robot extends TimedRobot 
{
    
    private DriveTrain driveTrain;
    private XboxController driveStick;
    private XboxController operateStick; 
    private Auton runningAuton;
    private PigeonIMU pigeon;
    private Limelight limelight;
    private Pillars pillars;
    private CargoManipulator cargo;
    private Elevator elevator;
    private HatchManipulator hatch;
    private Sonar sonar;
    private DriverStation driverStation;
    private Sucker sucker;

    @Override
    public void robotInit() 
    {
        driveTrain = new DriveTrain_SparkMAX(kLeftFrontID, kLeftRearID, kRightFrontID, kRightRearID);
        pigeon = new PigeonIMU(0);
        driveStick = new XboxController(0);
        operateStick = new XboxController(1);
        limelight = new Limelight("limelight");
        sonar = new Sonar(0);
        cargo = new CargoManipulator(kIntakeControlID, kHingeRightID, kHingeLeftID);
        hatch = new HatchManipulator(kHatchID);
        elevator = new Elevator(kElevatorLeftID, kElevatorRightID);
        pillars = new Pillars(kPillarsFront, kPillarsBack, kPillarWheels);
        sucker = new Sucker(10);

        runningAuton = new Auton(driveTrain, driveStick, operateStick, pigeon, limelight, elevator, hatch, pillars, sonar, cargo, sucker);
        
        driveTrain.setMotorMode(IdleMode.kCoast);
        
        limelight.setCamMode(1);
        limelight.setLEDMode(1);
        limelight.setStreamMode(1);

        SmartDashboard.putNumber("Angle", 0);
        driverStation = DriverStation.getInstance();
        
    }
    
    @Override
    public void robotPeriodic()
    {
        driveTrain.feedWatchdog();
        SmartDashboard.putNumber("Angle", cargo.getHingeAngle());
        SmartDashboard.putNumber("Front Pillar", pillars.getFrontHeight());
        SmartDashboard.putNumber("Rear Pillar", pillars.getRearHeight());
        driveTrain.feedWatchdog();
        SmartDashboard.putBoolean("Hatch", hatch.getHatch());
        SmartDashboard.putString("Hatch Distance", "Max: " + kArticulatorOut + " Current: " + hatch.getLocation());
        SmartDashboard.putNumber("Suck Current", sucker.getCurrent());
        driveTrain.feedWatchdog();
        //System.out.println(hatch.getLocation());
        // System.out.print("FRONT:" + pillars.getFrontHeight());
        // System.out.println(" || BACK:" + pillars.getRearHeight());
        
    } 

    @Override
    public void autonomousInit() 
    {

    }

    @Override
    public void autonomousPeriodic() 
    {
        run();
    }

    @Override
    public void teleopInit() 
    {

    }

    @Override
    public void teleopPeriodic() 
    {
        run();
    }

    @Override
    public void disabledInit()
    {
      
    }

    @Override
    public void disabledPeriodic()
    {
        operateStick.setRumble(RumbleType.kLeftRumble, 0);
        operateStick.setRumble(RumbleType.kRightRumble, 0);
        driveStick.setRumble(RumbleType.kLeftRumble, 0);
        driveStick.setRumble(RumbleType.kRightRumble, 0);
    }

    @Override
    public void testInit()
    {
      
    }

    @Override
    public void testPeriodic()
    {
      
        if(driveStick.getAButton())
        {
            runningAuton.getPillars().setRearPillar(-0.2);
        }
        else if(driveStick.getBButton())
        {
            runningAuton.getPillars().setRearPillar(0.2);
        }
        else
        {
            runningAuton.getPillars().setRearPillar(0);
        }
        
        if(driveStick.getXButton())
        {
            runningAuton.getPillars().setFrontPillar(-0.2);
        }
        else if(driveStick.getYButton())
        {
            runningAuton.getPillars().setFrontPillar(0.2);
        }
        else
        {
            runningAuton.getPillars().setFrontPillar(0);
        }

        if(driveStick.getStartButton())
        {
            runningAuton.getHatchManipulator().runArticulator(-0.5);
        }
        else if(driveStick.getBackButton())
        {
            runningAuton.getHatchManipulator().runArticulator(0.5);
        }
        else
        {
            runningAuton.getHatchManipulator().runArticulator(0);
        }


    }

    private void run()
    {
        if(runningAuton != null && !runningAuton.isFinished())
        {
            runningAuton.runAuton();
        }
        else if(runningAuton != null)
        {
            runningAuton.runTeleop();
        }
    }

}
