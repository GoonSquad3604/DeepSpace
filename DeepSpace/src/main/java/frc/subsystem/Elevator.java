package frc.subsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import static frc.robot.Constants.*;

public class Elevator
{

    private WPI_TalonSRX leftElevator, rightElevator;

    public Elevator(int elevatorLeftID, int elevatorRightID)
    {
        
        leftElevator = new WPI_TalonSRX(elevatorLeftID);
        rightElevator = new WPI_TalonSRX(elevatorRightID);
        leftElevator.setInverted(true);
        rightElevator.follow(leftElevator);

        //PID Settings
        leftElevator.config_kP(0, kElevatorP, kTimeoutMs);
        leftElevator.config_kI(0, kElevatorI, kTimeoutMs);
        leftElevator.config_kD(0, kElevatorD, kTimeoutMs);
        leftElevator.config_kF(0, kElevatorF, kTimeoutMs);

    }

    //Moves the elevator to the height.
    public void moveElevator(double height)
    {
        leftElevator.set(ControlMode.MotionMagic, height);
        //rightElevator.set(ControlMode.MotionMagic, height);
    }

    //Resets the sensor to a height.
    public void setHeight(double height)
    {
        leftElevator.setSelectedSensorPosition((int)height, 0, kTimeoutMs);
        rightElevator.setSelectedSensorPosition((int)height, 0, kTimeoutMs);
    }

    public WPI_TalonSRX getElevator()
    {
        return leftElevator;
    }

    //Returns the height of the sensor.
    public double getHeight()
    {
        return leftElevator.getSelectedSensorPosition();
    }

}