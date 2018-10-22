/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.IterativeRobot;

public class Robot extends IterativeRobot {
  

	public static WPI_TalonSRX leftMain;
	public static WPI_TalonSRX rightMain;
	public static WPI_TalonSRX leftSlave;
	public static WPI_TalonSRX rightSlave;
  


  @Override
  public void robotInit() {
    
    leftMain = new WPI_TalonSRX(15);
    rightMain = new WPI_TalonSRX(1);
    leftSlave = new WPI_TalonSRX(14);
    rightSlave = new WPI_TalonSRX(2);
    leftSlave.follow(leftMain);
	rightSlave.follow(rightMain);

	//for the lulz
    
  }

  @Override
  public void robotPeriodic() {
  
  }

  @Override
  public void autonomousInit() {
   
  }

  @Override
  public void autonomousPeriodic() {

  }

  @Override
  public void teleopPeriodic() {
  
  }

  
}
