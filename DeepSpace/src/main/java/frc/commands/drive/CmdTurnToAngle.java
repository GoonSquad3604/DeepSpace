/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.commands.drive;

import com.ctre.phoenix.sensors.PigeonIMU;
import frc.commands.AutonCommand;
import frc.robot.Constants;
import frc.subsystem.drivetrain.*;
import frc.vision.Limelight;

/**
 * Add your docs here.
 */
public class CmdTurnToAngle implements AutonCommand{


    private DriveTrain driveTrain;
    private PigeonIMU gyro;
    private double targetAngle;
    private double P = Constants.kTurnP;
    private double I = Constants.kTurnI;
    private double D = Constants.kTurnD;
    private double integral = 0;
    private double derivative = 0;
    private double previous_error = 0; 
    private Limelight lime;
    double[] ypr = new double[3];

    public CmdTurnToAngle(DriveTrain driveTrain, PigeonIMU gyro, double targetAngle)
    {
        this.driveTrain = driveTrain;
        this.targetAngle = targetAngle;
        this.gyro = gyro;
    }
    public CmdTurnToAngle(DriveTrain driveTrain, PigeonIMU gyro, Limelight lime)
    {
        this.driveTrain = driveTrain;
        this.targetAngle = -2000;
        this.gyro = gyro;
        this.lime = lime;
    }
    @Override
    public boolean isFinished() {
        gyro.getYawPitchRoll(ypr);
        return Math.abs(targetAngle - ypr[0]) < Constants.kTurnError;
    }

    @Override
    public void runTask() {
        gyro.getYawPitchRoll(ypr);
        driveTrain.arcadeDrive(0, PID(targetAngle));
    }

    @Override
    public double getStatus() {
        gyro.getYawPitchRoll(ypr);
        return ypr[0];
    }

    @Override
    public void init() {
        gyro.setYaw(0.0);
        gyro.getYawPitchRoll(ypr);
        if(lime != null)
        {
            targetAngle = lime.getTargetX();
        }
    }

    public double PID(double targetAngle){ 
        double error = targetAngle - ypr[0]; // Error = Target - Actual
        integral += (error*.02); // Integral is increased by the error*time (which is .02 seconds using normal IterativeRobot)
        derivative = (error - previous_error) / .02;
        return P*error + I*integral + D*derivative;
    }



}
