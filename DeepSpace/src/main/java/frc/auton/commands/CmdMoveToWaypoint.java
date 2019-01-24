package frc.auton.commands;

import frc.robot.Constants;
import frc.subsystem.DriveTrain;
import jaci.pathfinder.*;
import jaci.pathfinder.followers.EncoderFollower;
import jaci.pathfinder.modifiers.TankModifier;
public class CmdMoveToWaypoint implements AutonCommand
{

    private Waypoint[] waypoints;
    private DriveTrain drive;
    private EncoderFollower leftFollow, rightFollow;
    private Trajectory leftTrajectory, rightTrajectory;
    private Trajectory.Config config;
    private TankModifier modifier;

    public CmdMoveToWaypoint(DriveTrain drive, Waypoint... waypoints)
    {
        this.waypoints = waypoints;
        this.drive = drive;
        generateTrajectory(this.waypoints);
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
        drive.getLeftMotor().setSelectedSensorPosition(0,0,Constants.kTimeoutMs);
        drive.getRightMotor().setSelectedSensorPosition(0,0,Constants.kTimeoutMs);
    }
    private void generateTrajectory(Waypoint[] points)
    {
        config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC,Trajectory.Config.SAMPLES_HIGH,Constants.kDt,Constants.kVelocity,Constants.kAcceleration,Constants.kJerk);
    }

}