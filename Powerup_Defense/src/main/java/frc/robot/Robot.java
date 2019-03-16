

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.ExampleSubsystem;


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

leftMain = new WPI_TalonSRX(0);
rightMain = new WPI_TalonSRX(1);
rightSlave = new WPI_TalonSRX(2);
leftSlave = new WPI_TalonSRX(3);

leftSlave.follow(leftMain);
rightSlave.follow(rightMain);
rightMain.setInverted(true);
leftMain.setInverted(false);

drive = new DifferentialDrive(leftMain,rightMain);
  }

  @Override
  public void robotPeriodic() {
  }

  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
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

    drive.arcadeDrive(driveStick.getRawAxis(1), driveStick.getRawAxis(4));


  }

  @Override
  public void testPeriodic() {
  }
}
