/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.sensors.PigeonIMU;

import edu.wpi.first.wpilibj.TimedRobot;
import frc.auton.Auton;
import frc.auton.TestAuton;
import frc.subsystem.*;

public class Robot extends TimedRobot {

    private static DriveTrain drive;
    private Auton test;
    private PigeonIMU pigeon;
    private double yaw;
    @Override
    public void robotInit() 
    {
        drive = new DriveTrain(0,1,3,2);
        drive.getLeftMotor().setSelectedSensorPosition(0,0,0);
        pigeon = new PigeonIMU(drive.getRightSlave());
    }
    @Override
    public void robotPeriodic()
    {
        double[] ypr = new double[3];
        pigeon.getYawPitchRoll(ypr);
        yaw = ypr[0];
        System.out.println(yaw);
    } 
    public static DriveTrain getDriveTrain()
    {
        return drive;
    }

    @Override
    public void autonomousInit() {
        test = new TestAuton();
        System.out.println("!!!! !!!!" + test.size());
    }

    @Override
    public void autonomousPeriodic() {
        if(!test.isFinished())
        {
            test.runAuton();
        }
    }

    @Override
    public void teleopInit() {
    }

    @Override
    public void teleopPeriodic() {
        
    }

    @Override
    public void testInit() {
    }

    @Override
    public void testPeriodic() {
    }
    @Override
    public void disabledPeriodic()
    {
      
    }

}
