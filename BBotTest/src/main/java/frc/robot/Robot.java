/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.sensors.PigeonIMU;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.ConfigParameter;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Robot extends TimedRobot {
  
    CANSparkMax leftMain;
    CANSparkMax leftSlave;
    CANSparkMax rightMain;
    CANSparkMax rightSlave;

    CANSparkMax frontPillar;
    CANSparkMax rearPillar;

    WPI_TalonSRX pillarDrive;

    WPI_TalonSRX elevatorLeft;
    WPI_TalonSRX elevatorRight;

    XboxController driveStick;

    DifferentialDrive driveTrain;

    @Override
    public void robotInit() {
        
        leftMain = new CANSparkMax(0, MotorType.kBrushless);
        leftSlave = new CANSparkMax(1, MotorType.kBrushless);
        leftSlave.follow(leftMain);
        leftMain.setInverted(true);

        rightMain = new CANSparkMax(15, MotorType.kBrushless);
        rightSlave = new CANSparkMax(14, MotorType.kBrushless);
        rightSlave.follow(rightMain);
        rightMain.setInverted(true);
      
        frontPillar = new CANSparkMax(12, MotorType.kBrushless);
        frontPillar.setInverted(true);
        rearPillar = new CANSparkMax(13, MotorType.kBrushless);
        rearPillar.setInverted(true);

        pillarDrive = new WPI_TalonSRX(3);

        //elevator = new WPI_TalonSRX(3);

        driveTrain = new DifferentialDrive(leftMain, rightMain);

        driveStick = new XboxController(0);


    }

    @Override
    public void autonomousInit() {
    }

    @Override
    public void autonomousPeriodic() {
    }

    @Override
    public void teleopInit() {
     
    
    }

    double axis1 = 0;
    double axis4 = 0;

    @Override
    public void teleopPeriodic() {

      if(Math.abs(driveStick.getRawAxis(1)) > .1)
      {
        axis1 = driveStick.getRawAxis(1);
      }
      else{
        axis1 = 0.0;
      }

      if(Math.abs(driveStick.getRawAxis(4)) > .1)
      {
        axis4 = driveStick.getRawAxis(4);
      }
      else{
        axis4 = 0.0;
      }

      if(driveStick.getPOV() == 0){
        frontPillar.set(0.5);
        rearPillar.set(0.5);
      }
      else if(driveStick.getPOV() == 180){
        frontPillar.set(-0.5);
        rearPillar.set(-0.5);
      }
      else if(driveStick.getAButton()){
        frontPillar.set(0);
        rearPillar.set(-0.5);
      }
      else if(driveStick.getBButton()){
        frontPillar.set(0);
        rearPillar.set(0.5);
      }
      else if(driveStick.getXButton()){
        frontPillar.set(-0.5);
        rearPillar.set(0);
      }
      else if(driveStick.getYButton()){
        frontPillar.set(0.5);
        rearPillar.set(0);
      }
      else{
        frontPillar.set(0);
        rearPillar.set(0);
      }

      // if(driveStick.getBumper(Hand.kLeft)){
      //   elevator.set(0.5);
      // }
      // else if(driveStick.getBumper(Hand.kRight)){
      //   elevator.set(-0.5);
      // }
      // else{
      //   elevator.set(0);
      // }

      if(driveStick.getTriggerAxis(Hand.kLeft) - driveStick.getTriggerAxis(Hand.kRight) > 0.1){
        pillarDrive.set(-driveStick.getTriggerAxis(Hand.kLeft));
      }
      else if(driveStick.getTriggerAxis(Hand.kRight) - driveStick.getTriggerAxis(Hand.kLeft) > 0.1){
        pillarDrive.set(driveStick.getTriggerAxis(Hand.kRight));
      }
      else{
        pillarDrive.set(0);
      }
     
      driveTrain.arcadeDrive(axis1, axis4);

    }

    @Override
    public void testInit() {
    }

    @Override
    public void testPeriodic() {
    }

}
