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
import frc.auton.Auton;
import frc.auton.BlankAuton;
import frc.auton.DumbAuton;
import frc.auton.TestAuton;
import frc.subsystem.*;
import frc.vision.Limelight;

public class Robot extends TimedRobot {

    private DriveTrain drive;
    private XboxController driveStick; 
    private Auton runningAuton;
    private PigeonIMU pigeon;
    private Limelight limelight;
    private double yaw;
    private Object[] subSystems = {drive,driveStick,limelight,pigeon};
    @Override
    public void robotInit() 
    {
        drive = new DriveTrain(0,1,3,2);
        drive.getLeftMotor().setSelectedSensorPosition(0,0,0);
        pigeon = new PigeonIMU(drive.getRightSlave());
        driveStick = new XboxController(0);
        limelight = new Limelight("limelight");
    }
    @Override
    public void robotPeriodic()
    {
        double[] ypr = new double[3];
        pigeon.getYawPitchRoll(ypr);
        yaw = ypr[0];
        //System.out.println(yaw);
    } 
    public DriveTrain getDriveTrain()
    {
        return drive;
    }

    @Override
    public void autonomousInit() 
    {
        runningAuton = new Auton(drive,driveStick,pigeon,limelight);
        DumbAuton.addCommands(runningAuton);
        System.out.println("!!!! !!!!" + runningAuton.getSize());
    }

    @Override
    public void autonomousPeriodic() {
        run();
    }

    @Override
    public void teleopInit() {
        runningAuton = new Auton(drive,driveStick,pigeon,limelight);
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
