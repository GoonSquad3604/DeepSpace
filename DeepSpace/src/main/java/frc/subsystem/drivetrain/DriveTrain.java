/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.subsystem.drivetrain;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * Add your docs here.
 */
public abstract class DriveTrain extends DifferentialDrive{

    public DriveTrain(SpeedController leftMotor, SpeedController rightMotor){
        super(leftMotor, rightMotor);
    }

    public abstract void setLeft(double value);
    public abstract void setRight(double value);
    public abstract void setLeftPosition(double position);
    public abstract void setRightPosition(double position);
    public abstract double getLeftPosition();
    public abstract double getRightPosition();
    public abstract void resetDriveTrain();
    public abstract WPI_TalonSRX gyroTest();

}
