

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
  private WPI_TalonSRX leftMain;
  private WPI_TalonSRX rightMain;
  private WPI_TalonSRX leftSlave;
  private WPI_TalonSRX rightSlave;
  private DifferentialDrive drive;
  private XboxController driveStick;

  @Override
  public void robotInit() {

    driveStick = new XboxController(0);

    leftMain = new WPI_TalonSRX(1);
    rightMain = new WPI_TalonSRX(15);
    rightSlave = new WPI_TalonSRX(14);
    leftSlave = new WPI_TalonSRX(2);
    
    leftSlave.follow(leftMain);
    rightSlave.follow(rightMain);
    rightMain.setInverted(false);
    leftMain.setInverted(false);
    
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

    drive.arcadeDrive(driveStick.getRawAxis(1)*(.8), driveStick.getRawAxis(4)*(.8));
      
    }
   
  @Override
  public void testPeriodic() 
  {

  }
}
