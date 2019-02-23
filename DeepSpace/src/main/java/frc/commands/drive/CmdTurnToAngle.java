/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.commands.drive;

import com.ctre.phoenix.sensors.PigeonIMU;

import edu.wpi.first.wpilibj.Timer;
import frc.commands.AutonCommand;
import static frc.robot.Constants.*;
import frc.subsystem.drivetrain.*;
import frc.vision.Limelight;

public class CmdTurnToAngle implements AutonCommand
{

    private DriveTrain driveTrain;
    private PigeonIMU gyro;
    private double targetAngle;
    private double P = kTurnP;
    private double I = kTurnI;
    private double D = kTurnD;
    private double integral = 0;
    private double derivative = 0;
    private double previous_error = 0; 
    private Limelight limelight;
    private double[] ypr = new double[3];
    private Timer correctTime;
    private boolean runningTimer = false;

    /**
     * Turns to set angle
     */
    public CmdTurnToAngle(DriveTrain iDriveTrain, PigeonIMU iGyro, double iTargetAngle)
    {
        driveTrain = iDriveTrain;
        targetAngle = iTargetAngle;
        gyro = iGyro;
        correctTime = new Timer();
    }

    /**
     * Turn to limelight angle
     */
    public CmdTurnToAngle(DriveTrain iDriveTrain, PigeonIMU iGyro, Limelight iLimelight)
    {
        driveTrain = iDriveTrain;
        targetAngle = -2000;
        gyro = iGyro;
        limelight = iLimelight;
        correctTime = new Timer();
        limelight.setPipeline(kReflectiveTapePipeline);
    }

    @Override
    public boolean isFinished() {
        gyro.getYawPitchRoll(ypr);
        if((Math.abs(targetAngle) - Math.abs(ypr[0]) < kTurnError))
        {
            if(!runningTimer)
            {
                correctTime.start();
                runningTimer = true;
            }
            else if(correctTime.get() > 0.2)
            {
                return true;
            }
        }
        else if(runningTimer)
        {
            runningTimer = false;
            correctTime.reset();
            correctTime.stop();
        }
        return false;
    }

    @Override
    public void runTask() {
        gyro.getYawPitchRoll(ypr);
        driveTrain.arcadeDrive(0, PID(-targetAngle));
    }

    @Override
    public double getStatus() {
        gyro.getYawPitchRoll(ypr);
        return ypr[0];
    }

    @Override
    public void init() 
    {
        gyro.setYaw(0.0);
        gyro.getYawPitchRoll(ypr);
        if(limelight != null)
        {
            targetAngle = limelight.getTargetX();
        }
    }

    public double PID(double targetAngle)
    { 
        double error = targetAngle - ypr[0]; // Error = Target - Actual
        integral += (error*.02); // Integral is increased by the error*time (which is .02 seconds using normal IterativeRobot)
        derivative = (error - previous_error) / .02;
        return P*error + I*integral + D*derivative;
    }

}
