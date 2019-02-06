/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.ConfigParameter;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.TimedRobot;
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

    XboxController driveStick;

    DifferentialDrive driveTrain;

    PowerDistributionPanel pdp;

    @Override
    public void robotInit() {
        
        leftMain = new CANSparkMax(0, MotorType.kBrushless);
        leftSlave = new CANSparkMax(1, MotorType.kBrushless);
        leftSlave.follow(leftMain);
        leftMain.setInverted(true);
        leftMain.setParameter(ConfigParameter.kRampRate, 1);

        rightMain = new CANSparkMax(15, MotorType.kBrushless);
        rightSlave = new CANSparkMax(14, MotorType.kBrushless);
        rightSlave.follow(rightMain);
        rightMain.setInverted(true);
        rightMain.setParameter(ConfigParameter.kRampRate, 1);
        
    

        frontPillar = new CANSparkMax(12, MotorType.kBrushless);
        frontPillar.setInverted(true);
        rearPillar = new CANSparkMax(13, MotorType.kBrushless);
        rearPillar.setInverted(true);

        pillarDrive = new WPI_TalonSRX(2);

        driveTrain = new DifferentialDrive(leftMain, rightMain);

        driveStick = new XboxController(0);

        pdp = new PowerDistributionPanel();

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

    @Override
    public void teleopPeriodic() {

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

      // if(driveStick.getTriggerAxis(Hand.kLeft) - driveStick.getTriggerAxis(Hand.kRight) > 0.1){
      //   pillarDrive.set(-driveStick.getTriggerAxis(Hand.kLeft));
      // }
      // else if(driveStick.getTriggerAxis(Hand.kRight) - driveStick.getTriggerAxis(Hand.kLeft) > 0.1){
      //   pillarDrive.set(driveStick.getTriggerAxis(Hand.kRight));
      // }
      // else{
      //   pillarDrive.set(0);
      // }
      SmartDashboard.putNumber("Front Pillar", pdp.getCurrent(12));
      SmartDashboard.putNumber("Rear Pillar", pdp.getCurrent(13));
      driveTrain.arcadeDrive(0.8*driveStick.getRawAxis(1), -0.8*driveStick.getRawAxis(4));

      

    }

    @Override
    public void testInit() {
    }

    @Override
    public void testPeriodic() {
    }

}
