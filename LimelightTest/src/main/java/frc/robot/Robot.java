package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.sensors.PigeonIMU;

public class Robot extends TimedRobot {

  int P, I, D = 1;
  int integral, previous_error, setpoint = 0;
  PigeonIMU gyro;

  //Sets up drivetrain motors
  private WPI_TalonSRX leftMain = new WPI_TalonSRX(0);
  private WPI_TalonSRX leftSlave = new WPI_TalonSRX(1);
  private WPI_TalonSRX rightSlave = new WPI_TalonSRX(2);
  private WPI_TalonSRX rightMain = new WPI_TalonSRX(3);

  //The drivetrain object
  private DifferentialDrive drive;

  //Drive Stick
  private XboxController stick = new XboxController(0);

  //Initializing all the objects
  @Override
  public void robotInit() {
    
    leftSlave.follow(leftMain);
    rightSlave.follow(rightMain);
    drive = new DifferentialDrive(leftMain, rightMain);
    gyro = new PigeonIMU(rightSlave);
    gyro.setYaw(0.0, 10);
    
  }

  //Resets auton
  @Override
  public void autonomousInit() {
   
  }

  @Override
  public void autonomousPeriodic() 
  {
    
  }

  @Override
  public void teleopInit() 
  {
  }

  @Override
  public void teleopPeriodic()
  {

    double[] ypr = new double[3];
    gyro.getYawPitchRoll(ypr);
    System.out.println(ypr[0]);
    //DRIVING!!!
    drive.arcadeDrive(stick.getRawAxis(1),-stick.getRawAxis(4));

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
  
  }

}
