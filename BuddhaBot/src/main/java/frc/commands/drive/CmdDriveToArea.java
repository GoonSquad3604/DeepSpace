/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.commands.drive;

import frc.commands.AutonCommand;
import frc.subsystem.drivetrain.DriveTrain;
import frc.vision.Limelight;

/**
 * Add your docs here.
 */
public class CmdDriveToArea implements AutonCommand
{

    private Limelight limelight;
    private DriveTrain driveTrain;
    private double desiredArea;
    private double speed;
    public CmdDriveToArea(double iSpeed, double iDesiredArea, Limelight iLimelight, DriveTrain iDriveTrain){
        limelight = iLimelight;
        driveTrain = iDriveTrain;
        desiredArea = iDesiredArea;
        speed = iSpeed;
    }

    @Override
    public boolean isFinished() {
        return Math.abs(limelight.getTargetArea()-desiredArea) <= 1;
    }

    @Override
    public void runTask() {
        if(limelight.getTargetArea() > desiredArea)
        {
            driveTrain.arcadeDrive(speed, 0);
        }
        else if(limelight.getTargetArea() < desiredArea)
        {
            driveTrain.arcadeDrive(-speed, 0);
        }
    }

    @Override
    public double getStatus() {
        return limelight.getTargetArea();
    }

    @Override
    public void init() {

    }

}
