package frc.auton.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;

import frc.robot.Constants;
import frc.subsystem.DriveTrain;
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
    public boolean isFinished() {
        return leftFollow.isFinished() && rightFollow.isFinished();
    }

    @Override
    public void runTask() {
        drive.getLeftMotor().set(ControlMode.PercentOutput, leftFollow.calculate(drive.getLeftMotor().getSelectedSensorPosition(0)));
        drive.getRightMotor().set(ControlMode.PercentOutput, -rightFollow.calculate(-drive.getRightMotor().getSelectedSensorPosition(0)));
    }

    @Override
    public double getStatus() {
        return 0;
    }
    @Override
    public void init() {
        drive.getLeftMotor().setSelectedSensorPosition(0,0,Constants.kTimeoutMs);
        drive.getRightMotor().setSelectedSensorPosition(0,0,Constants.kTimeoutMs);
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
        leftFollow.configurePIDVA(Constants.kP, Constants.kI, Constants.kD, Constants.kVelocityRatio, Constants.kAccelerationRatio);

        rightFollow = new EncoderFollower(rightTrajectory);
        rightFollow.configureEncoder(0, 1024, Constants.kWheelDiameter);
        rightFollow.configurePIDVA(Constants.kP, Constants.kI, Constants.kD, Constants.kVelocityRatio, Constants.kAccelerationRatio);
    }

}