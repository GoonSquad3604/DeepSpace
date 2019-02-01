/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.ConfigParameter;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Talon;
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private CANSparkMax leftMain;
  private CANSparkMax rightMain;
  private CANSparkMax leftSlave;
  private CANSparkMax rightSlave;
  private Spark intake;
  private Spark hatchManipulator;
  private Spark elevator;
  private final int intakeId = 0;
  private final int hmId = 3;
  private final int elevatorID = 8;//TODO
  private DifferentialDrive drive;
  private XboxController driveStick;
  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  @Override
  public void robotInit() {
    
		//Drivetrain
    leftMain = new CANSparkMax(61, MotorType.kBrushless);
    leftSlave = new CANSparkMax(51, MotorType.kBrushless);
    rightMain = new CANSparkMax(22, MotorType.kBrushless);
    rightSlave = new CANSparkMax(33, MotorType.kBrushless);
    intake = new Spark(intakeId);
    hatchManipulator = new Spark(hmId);
    elevator  = new Spark(elevatorID);
    driveStick = new XboxController(0);
    
    leftMain.setParameter(ConfigParameter.kRampRate, 1);
    rightMain.setParameter(ConfigParameter.kRampRate, 1);
		leftSlave.follow(leftMain);
    rightSlave.follow(rightMain);
    drive = new DifferentialDrive(leftMain,rightMain);
    
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
    drive.arcadeDrive(0.8*driveStick.getRawAxis(1), 0.8*driveStick.getRawAxis(4));
    if(driveStick.getBumper(Hand.kLeft))
    {
      intake.set(1);
      System.out.println("never gonna give you up");
    }
    else if(driveStick.getBumper(Hand.kRight))
    {
      intake.set(-1);
      System.out.println("never gonna let you down");
    }
    else
    {
      intake.set(0);
    }


    if(driveStick.getXButton())
    {
      hatchManipulator.set(0.55);
    }
    else if(driveStick.getYButton())
    {
      hatchManipulator.set(-0.55);
    }
    else
    {
      hatchManipulator.set(0);
    }

    if(driveStick.getPOV()  == 0)
    {
      elevator.set(0.5);
    }
    else if(driveStick.getPOV() == 180)
    {
      elevator.set(-0.5);
    }
    else
    {
      elevator.set(0);
    }
  }

  @Override
  public void testInit() {
  }

  @Override
  public void testPeriodic() {
  }

}
