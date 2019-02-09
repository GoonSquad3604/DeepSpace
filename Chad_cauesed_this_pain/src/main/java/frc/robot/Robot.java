/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.ConfigParameter;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;


public class Robot extends TimedRobot {
  private CANSparkMax leftMain; 
  private CANSparkMax rightMain; 
  private CANSparkMax leftSlave; 
  private CANSparkMax rightSlave; 
  private DifferentialDrive drive;
  private XboxController driveStick;
  private TalonSRX elevatorRight;
  private TalonSRX elevatorLeft;
  private TalonSRX hindgeRight;
  private TalonSRX hindgeLeft;
  private TalonSRX intake;
  private TalonSRX beak;


  @Override
  public void robotInit() {

    leftMain = new CANSparkMax(0, MotorType.kBrushless);
    leftSlave = new CANSparkMax(1, MotorType.kBrushless);
    rightMain = new CANSparkMax(3, MotorType.kBrushless);
    rightSlave = new CANSparkMax(2, MotorType.kBrushless);
    driveStick = new XboxController(0);
    elevatorRight = new TalonSRX(0);
    elevatorLeft = new TalonSRX(0);
    hindgeRight = new TalonSRX(0);
    hindgeRight = new TalonSRX(0);
    hindgeRight = new TalonSRX(0);
    beak = new TalonSRX(0);

    leftMain.setParameter(ConfigParameter.kRampRate, 1);
    rightMain.setParameter(ConfigParameter.kRampRate, 1);
		leftSlave.follow(leftMain);
    rightSlave.follow(rightMain);
    elevatorLeft.follow(elevatorRight);
    elevatorLeft.setInverted(true);
    hindgeLeft.follow(hindgeRight);
    hindgeLeft.setInverted(true);

    drive = new DifferentialDrive(leftMain,rightMain);

  }

  @Override
  public void robotPeriodic() 
  {

  }
  @Override
  public void autonomousInit() 
  {
  }
  @Override
  public void autonomousPeriodic() 
  {

  }
  @Override
  public void teleopPeriodic() 
  {

    drive.arcadeDrive(-0.8*driveStick.getRawAxis(1), 0.6*driveStick.getRawAxis(4));

    if(driveStick.getBumper(Hand.kLeft))
    {

      intake.set(ControlMode.PercentOutput, 1);
      System.out.println("never gonna run around");

    }
    else if(driveStick.getBumper(Hand.kRight))
    {

      intake.set(ControlMode.PercentOutput, -1);
      System.out.println(" never gonna hurt you");

    }
    else
    {

      intake.set(ControlMode.PercentOutput, .0);

    }

    if(driveStick.getBButton())
    {

      beak.set(ControlMode.PercentOutput, .05);

    }

    else if(driveStick.getYButton())
    {


      beak.set(ControlMode.PercentOutput, -.05);

    }

    else
    {

      beak.set(ControlMode.PercentOutput, .0);

    }


    if(driveStick.getXButton())
    {

      hindgeRight.set(ControlMode.PercentOutput, .05);

    }
    else if(driveStick.getAButton())
    {

      hindgeRight.set(ControlMode.PercentOutput, -.05);

    }
    else
    {

      hindgeRight.set(ControlMode.PercentOutput, .0);

    }

    if(driveStick.getPOV()  == 0)
    {

      elevatorRight.set(ControlMode.PercentOutput, .4);
      System.out.println("never gonna give you up");
      elevatorRight.getSelectedSensorPosition();
      elevatorLeft.getSelectedSensorPosition();

    }
    else if(driveStick.getPOV() == 180)
    {

      elevatorRight.set(ControlMode.PercentOutput, -.4);
      System.out.println("never gonna let you down");
      elevatorRight.getSelectedSensorPosition();
      elevatorLeft.getSelectedSensorPosition();

    }

    else
    {

      elevatorRight.set(ControlMode.PercentOutput, .0);
      elevatorRight.getSelectedSensorPosition();
      elevatorLeft.getSelectedSensorPosition();

    }
  }
  @Override
  public void testPeriodic() 
  {

  }
}
