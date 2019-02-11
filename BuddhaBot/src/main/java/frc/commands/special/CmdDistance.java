/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.commands.special;

import frc.commands.AutonCommand;
import frc.subsystem.drivetrain.DriveTrain;
import frc.vision.Limelight;
import static frc.robot.Constants.*;

/**
 * Add your docs here.
 */
public class CmdDistance implements AutonCommand
{

    private Limelight limelight;
    private DriveTrain driveTrain;
    private double desiredDistance;
    public CmdDistance(double desiredDistance, Limelight limelight, DriveTrain driveTrain){
        this.limelight = limelight;
        this.driveTrain = driveTrain;
        this.desiredDistance = desiredDistance;
    }

    @Override
    public boolean isFinished() {
        return !limelight.doesTargetExist() || Math.abs(Math.abs(distance(limelight.getTargetArea())) - Math.abs(desiredDistance)) < kDistanceError;
    }

    @Override
    public void runTask() {
        driveTrain.arcadeDrive(-0.35,0);
    }

    @Override
    public double getStatus() {
        return distance(limelight.getTargetArea());
    }

    @Override
    public void init() {

    }

    private double distance(double area){
        return 113.67*Math.pow((0.6385),area);
    }

}
