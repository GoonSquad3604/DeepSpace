/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.sensors.PigeonIMU;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogOutput;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.auton.*;
import frc.subsystem.*;
import frc.subsystem.drivetrain.*;
import frc.vision.Limelight;
import frc.vision.Sonar;
import static frc.robot.Constants.*;

public class Robot extends TimedRobot 
{
    
    private DriveTrain drive;
    private XboxController driveStick;
    private XboxController operateStick; 
    private Auton runningAuton;
    private PigeonIMU pigeon;
    private Limelight limelight;
    private Pillars pillars;
    private CargoManipulator cargo;
    private Elevator elevator;
    private HatchManipulator blackLotus;
    private double yaw;
    private Sonar sonar;
    @Override
    public void robotInit() 
    {
        //drive = new DriveTrain_SparkMAX(kLeftFrontID, kLeftRearID, kRightFrontID, kRightRearID);
        
        //BUDDHA
        drive = new DriveTrain_TalonSRX(kLeftFrontID, kLeftRearID, kRightFrontID, kRightRearID);
        pigeon = new PigeonIMU(drive.gyroTest());
        driveStick = new XboxController(0);
        operateStick = new XboxController(1);
        limelight = new Limelight("limelight");
        sonar = new Sonar(0);
        //For NOT Buddha bot
        //addFinalBotSubsystems();
    }
    
    @Override
    public void robotPeriodic()
    {
        double[] ypr = new double[3];
        pigeon.getYawPitchRoll(ypr);
        yaw = ypr[0];
        driveStick.setRumble(RumbleType.kLeftRumble,0);
        driveStick.setRumble(RumbleType.kRightRumble,0);
        System.out.println(sonar.getInches());
    } 
    
    public DifferentialDrive getDriveTrain()
    {
        return drive;
    }

    @Override
    public void autonomousInit() 
    {
        runningAuton = new Auton(drive,driveStick,operateStick,pigeon,limelight,sonar);
        HatchPlaceAuton.addCommands(runningAuton);
    }

    @Override
    public void autonomousPeriodic() 
    {
        run();
    }

    @Override
    public void teleopInit() 
    {
        runningAuton = new Auton(drive,driveStick,operateStick,pigeon,limelight,sonar);
        BlankAuton.addCommands(runningAuton);
    }

    @Override
    public void teleopPeriodic() 
    {
        run();
    }

    @Override
    public void disabledPeriodic()
    {
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
        
        driveStick.setRumble(RumbleType.kLeftRumble,0);
        driveStick.setRumble(RumbleType.kRightRumble,0);
    }
    private void addFinalBotSubsystems()
    {
        cargo = new CargoManipulator(kIntakeControlID,kHingeRightID,kHingeLeftID);
        blackLotus = new HatchManipulator(kHatchLeftRightID,kHatchForwardBackID);
        elevator = new Elevator(kElevatorID,kElevatorSlaveID);
        pillars = new Pillars(kPillarsLeft,kPillarsRight,kPillarWheels);
    }

}
