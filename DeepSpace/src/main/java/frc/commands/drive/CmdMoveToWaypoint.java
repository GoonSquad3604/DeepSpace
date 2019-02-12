package frc.commands.drive;

import com.ctre.phoenix.motorcontrol.ControlMode;
import frc.commands.AutonCommand;
import static frc.robot.Constants.*;
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

    public CmdMoveToWaypoint(DriveTrain drive, Waypoint... waypoints)
    {
        this.waypoints = waypoints;
        this.drive = drive;
        generateTrajectory(this.waypoints);
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
        
        config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH, kDt, kVelocity, kAcceleration, kJerk);

        trajectory = Pathfinder.generate(points, config);
        
        modifier = new TankModifier(trajectory).modify(kRobotWidth);

        leftTrajectory = modifier.getLeftTrajectory();
        rightTrajectory = modifier.getRightTrajectory();

        leftFollow = new EncoderFollower(leftTrajectory);
        leftFollow.configureEncoder(0, 1024, kWheelDiameter);
        leftFollow.configurePIDVA(kDriveP, kDriveI, kDriveD, kVelocityRatio, kAccelerationRatio);

        rightFollow = new EncoderFollower(rightTrajectory);
        rightFollow.configureEncoder(0, 1024, kWheelDiameter);
        rightFollow.configurePIDVA(kDriveP, kDriveI, kDriveD, kVelocityRatio, kAccelerationRatio);
    }

}