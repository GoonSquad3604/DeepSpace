/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.sensors.PigeonIMU;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.auton.Auton;
import frc.auton.BlankAuton;
import frc.auton.DumbAuton;
import frc.auton.TestAuton;
import frc.subsystem.*;
import frc.subsystem.drivetrain.*;

public class Robot extends TimedRobot {
    
    private DriveTrain drive;
    private XboxController driveStick; 
    private Auton runningAuton;
    private PigeonIMU pigeon;
    private double yaw;
    private Object[] subSystems = {drive,driveStick};
    @Override
    public void robotInit() 
    {
        drive = new DriveTrain_SparkMAX(0,1,3,2);
        //pigeon = new PigeonIMU(drive.getRightSlave());
        driveStick = new XboxController(0);
    }
    @Override
    public void robotPeriodic()
    {
        double[] ypr = new double[3];
        pigeon.getYawPitchRoll(ypr);
        yaw = ypr[0];
        //System.out.println(yaw);
    } 
    public DifferentialDrive getDriveTrain()
    {
        return drive;
    }

    @Override
    public void autonomousInit() 
    {
        runningAuton = new Auton(drive,driveStick);
        DumbAuton.addCommands(runningAuton);
        System.out.println("!!!! !!!!" + runningAuton.getSize());
    }

    @Override
    public void autonomousPeriodic() {
        run();
    }

    @Override
    public void teleopInit() {
        runningAuton = new Auton(drive,driveStick);
        BlankAuton.addCommands(runningAuton);
    }

    @Override
    public void teleopPeriodic() {
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

}
