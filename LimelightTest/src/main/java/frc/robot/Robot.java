package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.vision.*;
public class Robot extends TimedRobot {

  private Limelight limelight;
  //Sets up drivetrain motors
  private WPI_TalonSRX leftMain = new WPI_TalonSRX(0);
  private WPI_TalonSRX leftSlave = new WPI_TalonSRX(1);
  private WPI_TalonSRX rightSlave = new WPI_TalonSRX(2);
  private WPI_TalonSRX rightMain = new WPI_TalonSRX(3);

  //The drivetrain object
  private DifferentialDrive drive;

  //Drive Stick
  private XboxController stick = new XboxController(0);

  //Current auton stage. The auton method runs different code depending on this value.
  private int stage = 0;

  
  private final int ocelateTime = 90;
  private int time = 0;
  private boolean ocelating = false;

  //Initializing all the objects
  @Override
  public void robotInit() {
    limelight = new Limelight();
    leftSlave.follow(leftMain);
    rightSlave.follow(rightMain);
    drive = new DifferentialDrive(leftMain, rightMain);
  }

  //Resets auton
  @Override
  public void autonomousInit() {
    stage = 0;
    time = 0;
    ocelating = false;
  }

  @Override
  public void autonomousPeriodic() 
  {
    /*
    if(stage == 0)
    {
      //Moves right until it finds a target
      if(limelight.doesTargetExist())
      {
        stage++;
      }
      else
      {
        drive.arcadeDrive(0,0.6);
      }
    }
    else if(stage == 1)
    {
      double skew = Math.abs(limelight.getTargetSkew());
      //If the target is on the left, move right. If the target is on the right, move left.
      if(skew>80)
      {
        if(ocelating)
        {
          drive.arcadeDrive(0,0.5);
        }
        else
        {
          drive.arcadeDrive(0,0.6);
        } 
      }
      else if(skew<10)
      {
        if(ocelating)
        {
          drive.arcadeDrive(0,-0.5);
        }
        else
        {
          drive.arcadeDrive(0,-0.6);
        } 
      }
      else
      {
        ocelating = true;
        if(time>ocelateTime)
        {
          stage++;
        }
      }
      if(ocelating)
      {
        time++;
      }
    }
    System.out.println(time);*/
  }

  @Override
  public void teleopInit() 
  {
  }

  @Override
  public void teleopPeriodic()
  {
    //DRIVING!!!
    drive.arcadeDrive(stick.getRawAxis(1),-stick.getRawAxis(4));

    
    stage = 0;
    time = 0;
    ocelating = false;
  }

  @Override
  public void testInit() {
  }

  @Override
  public void testPeriodic() {
  }

  @Override
  public void robotPeriodic()
  {
    if(limelight.doesTargetExist())
    {
      System.out.println(limelight);
    }
    else
    {
      System.out.println("TARGET NOT FOUND!");
    }
  }
}
