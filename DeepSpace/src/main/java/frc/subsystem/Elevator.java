package frc.subsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.Constants;

public class Elevator extends WPI_TalonSRX
{

    private TalonSRX followerElevator;

    public Elevator(int elevatorID, int followerElevatorID)
    {
        
        super(elevatorID);
        
        followerElevator = new TalonSRX(followerElevatorID);
        followerElevator.setInverted(true);
        followerElevator.follow(this);

        //PID Settings
        config_kP(0, Constants.kElevatorP, Constants.kTimeoutMs);
        config_kI(0, Constants.kElevatorI, Constants.kTimeoutMs);
        config_kD(0, Constants.kElevatorD, Constants.kTimeoutMs);
        config_kF(0, Constants.kElevatorF, Constants.kTimeoutMs);

    }

    public void moveElevator(double height)
    {
        this.
        set(ControlMode.MotionMagic, height);
    }

    public void setHeight(double height){
        setSelectedSensorPosition((int)height, 0, Constants.kTimeoutMs);
    }

    public double getHeight()
    {
        return getSelectedSensorPosition();
    }

}