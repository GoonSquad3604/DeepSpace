package frc.commands.drive;

import com.ctre.phoenix.motorcontrol.ControlMode;
import frc.commands.AutonCommand;
import frc.robot.Constants;
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

    public CmdMoveToWaypoint(DriveTrain iDrive, Waypoint... iWaypoints)
    {
        waypoints = iWaypoints;
        drive = iDrive;
        generateTrajectory(waypoints);
    }

    @Override
    public boolean isFinished() 
    {
        return leftFollow.isFinished() && rightFollow.isFinished();
    }

    @Override
    public void runTask() 
    {
        drive.setLeft(leftFollow.calculate((int)drive.getLeftPosition()));
        drive.setRight(rightFollow.calculate((int)drive.getRightPosition()));
    }

    @Override
    public double getStatus() 
    {
        return 0;
    }

    @Override
    public void init() 
    {
        drive.setLeftPosition(0);
        drive.setRightPosition(0);
    }
    
    private void generateTrajectory(Waypoint[] points)
    {
        
        config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH, Constants.kDt, Constants.kVelocity, Constants.kAcceleration, Constants.kJerk);

        trajectory = Pathfinder.generate(points, config);
        
        modifier = new TankModifier(trajectory).modify(Constants.kRobotWidth);

        leftTrajectory = modifier.getLeftTrajectory();
        rightTrajectory = modifier.getRightTrajectory();

        leftFollow = new EncoderFollower(leftTrajectory);
        leftFollow.configureEncoder(0, 1024, Constants.kWheelDiameter);
        leftFollow.configurePIDVA(Constants.kDriveP, Constants.kDriveI, Constants.kDriveD, Constants.kVelocityRatio, Constants.kAccelerationRatio);

        rightFollow = new EncoderFollower(rightTrajectory);
        rightFollow.configureEncoder(0, 1024, Constants.kWheelDiameter);
        rightFollow.configurePIDVA(Constants.kDriveP, Constants.kDriveI, Constants.kDriveD, Constants.kVelocityRatio, Constants.kAccelerationRatio);
    }

}