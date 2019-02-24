package frc.subsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;

import static frc.robot.Constants.*;

public class Elevator
{

    private WPI_TalonSRX leftElevator, rightElevator;
    private DigitalInput lowerLimit;

    /**
     * sets IDs, left Inverted, right follow left, and Pid settings
     * @param elevatorLeftID sets ID for left elevator motor
     * @param elevatorRightID sets ID for right elevator motor
     */
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

        leftElevator.configMotionCruiseVelocity(kElevatorCargoVel, kTimeoutMs);
        leftElevator.configMotionAcceleration(kElevatorCargoAcc, kTimeoutMs);

        lowerLimit = new DigitalInput(kElevatorLimitSensor);

    }

    /**
     * Moves the elevator to the height
     * @param height defines height of elevator
     */
    public void moveElevator(double height)
    {
        leftElevator.set(ControlMode.MotionMagic, height);
        //rightElevator.set(ControlMode.MotionMagic, height);
    }

    /**
     * Moves the elevator to the height
     * @param pwr defines power of elevator 
     */
    public void setPower(double pwr)
    {
        leftElevator.set(pwr);
        //rightElevator.set(ControlMode.MotionMagic, height);
    }

    /**
     * Resets the sensor to a height.
     * @param height defines height of elevator
     */
    public void setHeight(double height)
    {
        System.out.println("RESET THE ELEVATOR");
        leftElevator.setSelectedSensorPosition((int)height, 0, kTimeoutMs);
        rightElevator.setSelectedSensorPosition((int)height, 0, kTimeoutMs);
    }
    /**
     * returns left elevator
     * @return Left elevator
     */
    public WPI_TalonSRX getElevator()
    {
        return leftElevator;
    }

    /**
     * Returns the height of the sensor.
     * @return hieight of sensor
     */
    public double getHeight()
    {
        return leftElevator.getSelectedSensorPosition();
    }

    public boolean getLimit()
    {
        return !lowerLimit.get();
    }

}