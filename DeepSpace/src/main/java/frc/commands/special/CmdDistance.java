/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.commands.special;

import frc.commands.AutonCommand;
import frc.vision.Limelight;

/**
 * Add your docs here.
 */
public class CmdDistance implements AutonCommand{

    private Limelight limelight;

    public CmdDistance(Limelight limelight){
        this.limelight = limelight;
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public void runTask() {

    }

    @Override
    public double getStatus() {
        return distance(limelight.getTargetArea());
    }

    @Override
    public void init() {

    }

    private double distance(double area){
        return 0;
    }

}
