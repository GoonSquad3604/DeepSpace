/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.Rev2mDistanceSensor;
import com.revrobotics.Rev2mDistanceSensor.Port;
import com.revrobotics.Rev2mDistanceSensor.Unit;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot 
{
  private Sonar sonar;
  private WPI_TalonSRX leftFront;
  private WPI_TalonSRX leftRear;
  private WPI_TalonSRX rightFront;
  private WPI_TalonSRX rightRear;
  private DifferentialDrive drive;
  private XboxController stick;
  private final double kDistanceToStopFar = 123.0;
  private Rev2mDistanceSensor sensor;
  @Override
  public void robotInit() 
  {
    leftFront = new WPI_TalonSRX(0);
    leftRear = new WPI_TalonSRX(1);
    rightRear = new WPI_TalonSRX(2);
    rightFront = new WPI_TalonSRX(3);
    sonar = new Sonar(0);
    leftRear.follow(leftFront);
    rightRear.follow(rightFront);
    drive = new DifferentialDrive(leftFront,rightFront);
    stick = new XboxController(0);
    sensor = new Rev2mDistanceSensor(Port.kOnboard);
    //sensor.setAutomaticMode(true);
  }

  @Override
  public void robotPeriodic()
  {
    //System.out.println(sensor.getRange(Unit.kInches));
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
  public void teleopPeriodic() 
  {
    if(stick.getAButton())
    {
      drive.arcadeDrive(0,0);
    }
    else
    {
      drive.arcadeDrive(stick.getRawAxis(1)*0.75,-stick.getRawAxis(4)*0.75);
    }
  }

  @Override
  public void testInit() {
  }

  @Override
  public void testPeriodic() {
  }

}
