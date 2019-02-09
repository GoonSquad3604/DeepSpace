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
  private WPI_TalonSRX intake;
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
    
    // elevatorRight = new WPI_TalonSRX(0);
    // elevatorLeft = new WPI_TalonSRX(0);
    
    // hindgeRight = new WPI_TalonSRX(0);
    // hindgeLeft = new WPI_TalonSRX(0);
    // intake = new WPI_TalonSRX(0);
    
    // beak = new WPI_TalonSRX(0);
    
    frontPiller = new CANSparkMax(0, MotorType.kBrushless);
    backPiller = new CANSparkMax(0, MotorType.kBrushless);
    pillerWheels = new WPI_TalonSRX(3);
    
    driveStick = new XboxController(0);
    operatorStick = new XboxController(1);

    leftMain.setParameter(ConfigParameter.kRampRate, 1);
    rightMain.setParameter(ConfigParameter.kRampRate, 1);
		leftSlave.follow(leftMain);
    rightSlave.follow(rightMain);
    rightMain.setInverted(true);
    leftMain.setInverted(true);
    
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

    drive.arcadeDrive(-0.8*driveStick.getRawAxis(1), 0.6*driveStick.getRawAxis(4));

    if(operatorStick.getYButton())
    {

      frontPiller.set(.05);
      

    }
    else if(operatorStick.getBButton()) 
    {

      frontPiller.set(-.05);

    }
    else
    {

      frontPiller.set(0);

    }

    if(operatorStick.getAButton())
    {

      backPiller.set(.05);
      
    }
    else if(operatorStick.getXButton()) 
    {

      backPiller.set(-.05);

    }
    else
    {

      backPiller.set(0);

    }

    if(operatorStick.getBumper(Hand.kLeft)){

      backPiller.set(.05);
      frontPiller.set(.05);

    }
    else if(operatorStick.getBumper(Hand.kRight))
    {

      backPiller.set(-.05);
      frontPiller.set(-.05);

    }
    else
    {

      backPiller.set(0);
      frontPiller.set(0);

    }

    if(operatorStick.getBackButton())
    {

      pillerWheels.set(.05);

    }
    else
    {

      pillerWheels.set(0);

    }

    // if(driveStick.getBumper(Hand.kLeft))
    // {

    //   intake.set(ControlMode.PercentOutput, 1);
    //   System.out.println("never gonna run around");

    // }
    // else if(driveStick.getBumper(Hand.kRight))
    // {

    //   intake.set(ControlMode.PercentOutput, -1);
    //   System.out.println(" never gonna hurt you");

    // }
    // else
    // {

    //   intake.set(ControlMode.PercentOutput, .0);

    // }

    /*if(operatorStick.getBButton())
    {

      beak.set(ControlMode.PercentOutput, .05);

    }
    else if(operatorStick.getYButton())
    {


      beak.set(ControlMode.PercentOutput, -.05);

    }
    else
    {

      beak.set(ControlMode.PercentOutput, .0);

    }*/


    // if(driveStick.getXButton())
    // {

    //   hindgeRight.set(ControlMode.PercentOutput, .05);

    // }
    // else if(driveStick.getAButton())
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
