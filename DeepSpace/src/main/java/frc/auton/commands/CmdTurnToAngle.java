/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.auton.commands;

import frc.subsystem.DriveTrain;

/**
 * Add your docs here.
 */
public class CmdTurnToAngle implements AutonCommand{


    private DriveTrain drive;
    private double angle;

    public CmdTurnToAngle(DriveTrain drive, double angle)
    {
        this.drive = drive;
        this.angle = angle;
    }
    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void runTask() {

    }

    @Override
    public double getStatus() {
        return 0;
    }

    @Override
    public void init() {

    }

}
