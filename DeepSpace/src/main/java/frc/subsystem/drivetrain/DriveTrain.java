/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.subsystem.drivetrain;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * Add your docs here.
 */
public abstract class DriveTrain extends DifferentialDrive{

    public DriveTrain(SpeedController leftMotor, SpeedController rightMotor){
        super(leftMotor, rightMotor);
    }

    public abstract int getLeftPosition();
    public abstract int getRightPosition();
    public abstract void resetDriveTrain();

}
