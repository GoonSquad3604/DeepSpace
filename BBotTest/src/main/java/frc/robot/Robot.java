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

    WPI_TalonSRX sucker;
    WPI_TalonSRX hinge;
    WPI_TalonSRX hinge2;

    XboxController driveStick;
    XboxController operatorStick;

    DifferentialDrive driveTrain;



    double position = 0;
    double initPosition = 0;

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

        pillarDrive = new WPI_TalonSRX(2);
        pillarDrive.setInverted(true);

        elevatorLeft = new WPI_TalonSRX(6);
        elevatorLeft.setInverted(true);
        elevatorRight = new WPI_TalonSRX(5);

        sucker = new WPI_TalonSRX(3);
        hinge = new WPI_TalonSRX(8);
        hinge2 = new WPI_TalonSRX(7);

        driveTrain = new DifferentialDrive(leftMain, rightMain);

        driveStick = new XboxController(0);
        operatorStick = new XboxController(1);


    }

    @Override
    public void autonomousInit() {
    }

    @Override
    public void autonomousPeriodic() {
    }

    @Override
    public void teleopInit() {
      initPosition = rearPillar.getEncoder().getPosition();
    
    }

    double axis1 = 0;
    double axis4 = 0;

    @Override
    public void teleopPeriodic() {

      
      axis1 = (Math.abs(driveStick.getRawAxis(1)) > .1) ? 0.9 * driveStick.getRawAxis(1) : 0.0;
      axis4 = (Math.abs(driveStick.getRawAxis(4)) > .1) ? 0.9 * driveStick.getRawAxis(4) : 0.0;
      

      // if(operatorStick.getPOV() == 0){
      //   frontPillar.set(0.5);
      //   rearPillar.set(0.5);
      //   position = rearPillar.getEncoder().getPosition();
      // }
      // else if(operatorStick.getPOV() == 180){
      //   frontPillar.set(-0.5);
      //   rearPillar.set(-0.5);
      // }
      if(operatorStick.getAButton()){
        frontPillar.set(0);
        rearPillar.set(-0.5);
      }
      else if(operatorStick.getBButton()){
        frontPillar.set(0);
        rearPillar.set(0.5);
      }
      else if(operatorStick.getXButton()){
        frontPillar.set(-0.5);
        rearPillar.set(0);
      }
      else if(operatorStick.getYButton()){
        frontPillar.set(0.5);
        rearPillar.set(0);
      }
      else{
        frontPillar.set(0);
        rearPillar.set(0);
      }

      if(operatorStick.getStartButton()){
        elevatorLeft.set(0.7);
        elevatorRight.set(0.7);
      }
      else if(operatorStick.getBackButton()){
        elevatorLeft.set(-0.7);
        elevatorRight.set(-0.7);
      }
      else{
        elevatorLeft.set(0);
        elevatorRight.set(0);
      }

      if(operatorStick.getPOV() == 180){
        hinge.set(0.60);
        hinge2.set(-0.60);
      }
      else if(operatorStick.getPOV() == 900){
        hinge.set(0.80);
        hinge2.set(-0.80);
      }
      else if(operatorStick.getPOV() == 0){
        hinge.set(-0.60);
        hinge2.set(0.60);
      }
      else{
        hinge.set(0);
        hinge2.set(0);
      }

      if(operatorStick.getBumper(Hand.kLeft)){
        sucker.set(1);
      }
      else if(operatorStick.getBumper(Hand.kRight)){
        sucker.set(-1);
      }
      else{
        sucker.set(0);
      }
      
      
      //pillarDrive.set(driveStick.getTriggerAxis(Hand.kRight));
      
      driveTrain.arcadeDrive(axis1, -axis4);
      
      System.out.println(hinge.getSensorCollection().getAnalogInRaw());
    }

    @Override
    public void testInit() 
    {
      
    }

    @Override
    public void testPeriodic() 
    {

    }

}
