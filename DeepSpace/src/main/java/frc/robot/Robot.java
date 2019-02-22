/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.sensors.PigeonIMU;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
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
    private Sonar sonar;
    private double yaw;  
    @Override
    public void robotInit() 
    {
        drive = new DriveTrain_SparkMAX(kLeftFrontID, kLeftRearID, kRightFrontID, kRightRearID);
        pigeon = new PigeonIMU(0);
        driveStick = new XboxController(0);
        operateStick = new XboxController(1);
        limelight = new Limelight("limelight");
        addFinalBotSubsystems();
        sonar = new Sonar(0);
        runningAuton = new Auton(drive,driveStick,operateStick,pigeon,limelight,elevator/*,blackLotus*/,pillars,sonar,cargo);
    }
    
    @Override
    public void robotPeriodic()
    {
        double[] ypr = new double[3];
        pigeon.getYawPitchRoll(ypr);
        yaw = ypr[0];
        /*
        System.out.println(pillars.getHeight());
        System.out.println("FRONT:" + pillars.getFrontHeight());
        System.out.println("BACK:" + pillars.getRearHeight());*/
    } 
    
    public DifferentialDrive getDriveTrain()
    {
        return drive;
    }

    @Override
    public void autonomousInit() 
    {
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
    }
    private void addFinalBotSubsystems()
    {
        cargo = new CargoManipulator(kIntakeControlID,kHingeRightID,kHingeLeftID);
        //blackLotus = new HatchManipulator(kHatchLeftRightID,kHatchForwardBackID);
        elevator = new Elevator(kElevatorLeftID,kElevatorRightID);
        pillars = new Pillars(kPillarsFront,kPillarsBack,kPillarWheels);
    }

}
