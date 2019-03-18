package frc.commands.drive;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.sensors.PigeonIMU;

import edu.wpi.first.wpilibj.Notifier;
import frc.commands.AutonCommand;
import static frc.robot.Constants.*;

import java.io.IOException;

import frc.subsystem.drivetrain.*;
import jaci.pathfinder.*;
import jaci.pathfinder.followers.EncoderFollower;
import jaci.pathfinder.modifiers.TankModifier;

public class CmdMoveToWaypoint implements AutonCommand
{
    //Moves the robot to a waypoint using jaci pathfinder.
    private Waypoint[] waypoints;
    private DriveTrain drive;
    private EncoderFollower leftFollow, rightFollow;
    private Trajectory leftTrajectory, rightTrajectory;
    private Trajectory.Config config;
    private TankModifier modifier;
    private Trajectory trajectory;
    private Notifier notifier;
    private boolean ret;
    private PigeonIMU gyro;

    public CmdMoveToWaypoint(DriveTrain iDrive,PigeonIMU iGyro, String pathName)
    {
        drive = iDrive;
        try 
        {
            leftTrajectory = PathfinderFRC.getTrajectory(pathName + ".left");
            rightTrajectory = PathfinderFRC.getTrajectory(pathName + ".right");
        } catch (IOException e) 
        {
			e.printStackTrace();
		}
        leftFollow = new EncoderFollower(leftTrajectory);
        leftFollow.configureEncoder(0, 1012, kWheelDiameter);
        leftFollow.configurePIDVA(kDriveP, kDriveI, kDriveD, kVelocityRatio, kAccelerationRatio);
        rightFollow = new EncoderFollower(rightTrajectory);
        rightFollow.configureEncoder(0, 1012, kWheelDiameter);
        rightFollow.configurePIDVA(kDriveP, kDriveI, kDriveD, kVelocityRatio, kAccelerationRatio);
        gyro = iGyro;
        notifier = new Notifier(this::followPath);
        notifier.startPeriodic(leftTrajectory.get(0).dt);
        ret = false;
    }

    @Override
    public boolean isFinished() 
    {
        return ret;//leftFollow.isFinished() && rightFollow.isFinished();
    }

    @Override
    public void runTask() 
    {
        
    }

    @Override
    public double getStatus() 
    {
        return 0;
    }

    @Override
    public void end()
    {
        notifier.stop();
    }
    @Override
    public void init() 
    {
        drive.setLeftPosition(0);
        drive.setRightPosition(0);
    }

    private void followPath()
    {
        if (leftFollow.isFinished() || rightFollow.isFinished()) 
        {
            notifier.stop();
            ret = true;
        } 
        else 
        {
            double left_speed = leftFollow.calculate((int)(drive.getLeftPosition() * 1012));
            double right_speed = leftFollow.calculate((int)(drive.getRightPosition() * 1012));
            double[] ypr = new double[3];
            gyro.getYawPitchRoll(ypr);
            double heading = ypr[0];
            double desired_heading = Pathfinder.r2d(leftFollow.getHeading());
            double heading_difference = Pathfinder.boundHalfDegrees(desired_heading - heading);
            double turn =  0.8 * (-1.0/80.0) * heading_difference;
            drive.setLeft(left_speed + turn);
            drive.setRight(right_speed - turn);
        }
    }
}