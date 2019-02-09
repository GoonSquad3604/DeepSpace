/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.commands.drive;

import frc.commands.AutonCommand;
import frc.subsystem.drivetrain.DriveTrain;
import frc.vision.Sonar;
import static frc.robot.Constants.*;

/**
 * Add your docs here.
 */
public class CmdDriveSonar implements AutonCommand
{

    private Sonar sonar;
    private DriveTrain driveTrain;
    private double desiredDistance;
    public CmdDriveSonar(double iDesiredDistance, Sonar iSonar, DriveTrain iDriveTrain){
        sonar = iSonar;
        driveTrain = iDriveTrain;
        desiredDistance = iDesiredDistance;
    }

    @Override
    public boolean isFinished() {
        return Math.abs(Math.abs(sonar.getInches() - Math.abs(desiredDistance))) < kDistanceError;
    }

    @Override
    public void runTask() {
        driveTrain.arcadeDrive(-0.35,0);
    }

    @Override
    public double getStatus() {
        return sonar.getInches();
    }

    @Override
    public void init() {

    }

}
