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
import frc.auton.sandstorm.lvl1.left.*;
import frc.auton.sandstorm.lvl1.right.*;
import frc.auton.sandstorm.lvl2.left.*;
import frc.auton.sandstorm.lvl2.right.*;

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
    private HatchManipulator blackLotus;
    private Sonar sonar;
    private DriverStation driverStation;
    
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
        //blackLotus = new HatchManipulator(kHatchLeftRightID, kHatchForwardBackID);
        elevator = new Elevator(kElevatorLeftID, kElevatorRightID);
        pillars = new Pillars(kPillarsFront, kPillarsBack, kPillarWheels);

        runningAuton = new Auton(driveTrain, driveStick, operateStick, pigeon, limelight, elevator/*,blackLotus*/, pillars, sonar, cargo);
        
        driveTrain.setMotorMode(IdleMode.kCoast);
        
        limelight.setCamMode(1);
        limelight.setLEDMode(1);

        SmartDashboard.putNumber("Angle", 0);
        driverStation = DriverStation.getInstance();
    }
    
    @Override
    public void robotPeriodic()
    {
        
        SmartDashboard.putNumber("Angle", cargo.getHingeAngle());
        SmartDashboard.putNumber("Front Pillar", pillars.getFrontHeight());
        SmartDashboard.putNumber("Rear Pillar", pillars.getRearHeight());
        driveTrain.feedWatchdog();
        System.out.println(cargo.getSensorValue());
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
        run(true);
    }

    @Override
    public void teleopInit() 
    {

    }

    @Override
    public void teleopPeriodic() 
    {
        run(false);
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
      
    }

    private void run(boolean auton)
    {
        if(runningAuton != null && !runningAuton.isFinished())
        {
            runningAuton.runAuton();
        }
        else if(runningAuton != null)
        {
            runningAuton.runTeleop(auton);
        }
    }

    public DriveTrain getDriveTrain()
    {
        return driveTrain;
    }

}
