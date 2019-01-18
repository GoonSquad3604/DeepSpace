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
  private double turnAmount = 0.5;
  private boolean ocelating = false;

  private final double kpAim = -0.1;
  private final double kpDistance = -0.1;
  private final double aimCommand = 0.5;

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
    if(stage == 0)
    {
      drive.arcadeDrive(0,-.4);
      if(limelight.doesTargetExist())
      {
        stage++;
      }
    }
    else if(stage == 1)
    {
    }
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
