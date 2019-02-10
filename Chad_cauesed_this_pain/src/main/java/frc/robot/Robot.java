/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
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
  private XboxController operatorStick;
  private WPI_TalonSRX elevatorRight;
  private WPI_TalonSRX elevatorLeft;
  private WPI_TalonSRX hindgeRight;
  private WPI_TalonSRX hindgeLeft;
  private WPI_TalonSRX ballIntake;
  private WPI_TalonSRX beak;
  private CANSparkMax frontPiller;
  private CANSparkMax backPiller;
  private WPI_TalonSRX pillerWheels;


  @Override
  public void robotInit() {

    leftMain = new CANSparkMax(0, MotorType.kBrushless);
    leftSlave = new CANSparkMax(1, MotorType.kBrushless);
    rightMain = new CANSparkMax(15, MotorType.kBrushless);
    rightSlave = new CANSparkMax(14, MotorType.kBrushless);
    
    // elevatorRight = new WPI_TalonSRX(5);
    // elevatorLeft = new WPI_TalonSRX(6);
    
    // hindgeRight = new WPI_TalonSRX(7);
    // hindgeLeft = new WPI_TalonSRX(8);
    // ballIntake = new WPI_TalonSRX(3);
    
    // beak = new WPI_TalonSRX(9);
    
    frontPiller = new CANSparkMax(12, MotorType.kBrushless);
    backPiller = new CANSparkMax(13, MotorType.kBrushless);
    pillerWheels = new WPI_TalonSRX(2);
    
    driveStick = new XboxController(0);
    operatorStick = new XboxController(1);

    leftMain.setParameter(ConfigParameter.kRampRate, 1);
    rightMain.setParameter(ConfigParameter.kRampRate, 1);
		leftSlave.follow(leftMain);
    rightSlave.follow(rightMain);
    rightMain.setInverted(true);
    leftMain.setInverted(true);

    pillerWheels.setInverted(true);
    
    // elevatorLeft.follow(elevatorRight);
    // elevatorLeft.setInverted(true);
    
    //hindgeLeft.follow(hindgeRight);
    //hindgeLeft.setInverted(true);
    
    backPiller.setInverted(true);

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

    drive.arcadeDrive(0.8*driveStick.getRawAxis(1), 0.7*driveStick.getRawAxis(4));

    if(driveStick.getYButton())
    {

      frontPiller.set(.05);
      backPiller.set(0);

    }
    else if(driveStick.getBButton()) 
    {

      frontPiller.set(-.05);
      backPiller.set(0);

    }
    else
    {

      frontPiller.set(0);
      backPiller.set(0);

    }

    if(driveStick.getAButton())
    {

      backPiller.set(.05);
      frontPiller.set(0);
      
    }
    else if(driveStick.getXButton()) 
    {

      backPiller.set(-.05);
      frontPiller.set(0);

    }
    else
    {

      backPiller.set(0);
      frontPiller.set(0);

    }

    if(driveStick.getBumper(Hand.kLeft))
    {

      backPiller.set(.05);
      frontPiller.set(.05);

    }
    else if(driveStick.getBumper(Hand.kRight))
    {

      backPiller.set(-.05);
      frontPiller.set(-.05);

    }
    else
    {

      backPiller.set(0);
      frontPiller.set(0);

    }

    if(operatorStick.getTriggerAxis(Hand.kLeft) - driveStick.getTriggerAxis(Hand.kRight) > 0.1){
      pillerWheels.set(-driveStick.getTriggerAxis(Hand.kLeft));
    }
    else if(operatorStick.getTriggerAxis(Hand.kRight) - driveStick.getTriggerAxis(Hand.kLeft) > 0.1){
      pillerWheels.set(driveStick.getTriggerAxis(Hand.kRight));
    }
    else{
      pillerWheels.set(0);
    }

    // if(operatorStick.getBumper(Hand.kLeft))
    // {

    //   ballIntake.set(ControlMode.PercentOutput, 1);
    //   System.out.println("never gonna run around");

    // }
    // else if(operatorStick.getBumper(Hand.kRight))
    // {

    //   ballIntake.set(ControlMode.PercentOutput, -1);
    //   System.out.println(" never gonna hurt you");

    // }
    // else
    // {

    //   ballIntake.set(ControlMode.PercentOutput, .0);

    // }

    /*if(operatorStick.getBButton())
    {

      beak.set(.05);

    }
    else if(operatorStick.getYButton())
    {


      beak.set(-.05);

    }
    else
    {

      beak.set(ControlMode.PercentOutput, .0);

    }*/


    // if(operatorStick.getXButton())
    // {

    //   hindgeRight.set(ControlMode.PercentOutput, .05);

    // }
    // else if(operatorStick.getAButton())
    // {

    //   hindgeRight.set(ControlMode.PercentOutput, -.05);

    // }
    // else
    // {

    //   hindgeRight.set(ControlMode.PercentOutput, .0);

    // }

    // elevatorRight.set(operatorStick.getRawAxis(1)*.3);
  
    //   if(operatorStick.getRawAxis(1) >0)
    //   {
    //     System.out.println("never gonna give you up");
    //   }
    //   else if(operatorStick.getRawAxis(1) < 0)
    //   {
    //     System.out.println("never gonna let you down");
    //   }

      
    }
   
  @Override
  public void testPeriodic() 
  {

  }
}
