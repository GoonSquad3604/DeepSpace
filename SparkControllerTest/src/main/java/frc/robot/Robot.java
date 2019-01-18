/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.ConfigParameter;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Robot extends IterativeRobot {
  
  CANSparkMax leftMain;
  CANSparkMax leftSlave;
  CANSparkMax rightMain;
  CANSparkMax rightSlave;

  DifferentialDrive driveTrain;

  XboxController driveStick;

  @Override
  public void robotInit() {
    
    leftMain = new CANSparkMax(61, MotorType.kBrushless);
    leftSlave = new CANSparkMax(51, MotorType.kBrushless);
    rightMain = new CANSparkMax(22, MotorType.kBrushless);
    rightSlave = new CANSparkMax(33, MotorType.kBrushless);

    leftMain.setParameter(ConfigParameter.kRampRate, 1);
    rightMain.setParameter(ConfigParameter.kRampRate, 1);

    leftSlave.follow(leftMain);
    rightSlave.follow(rightMain);

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

  @Override
  public void teleopPeriodic() {

    driveTrain.arcadeDrive(-0.8*driveStick.getRawAxis(1), 0.8*driveStick.getRawAxis(4));
    //Timer.delay(0.02);

  }

  @Override
  public void testInit() {
  }

  @Override
  public void testPeriodic() {
  }

}
