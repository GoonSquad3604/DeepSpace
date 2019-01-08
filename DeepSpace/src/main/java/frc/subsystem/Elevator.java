package frc.subsystem;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.Constants;

public class Elevator extends WPI_TalonSRX{

    public Elevator(int elevatorID){
        
        super(elevatorID);
        
        //Cargo Settings
        config_kP(0, Constants.kElevatorCargoP, Constants.kTimeoutMs);
        config_kI(0, Constants.kElevatorCargoI, Constants.kTimeoutMs);
        config_kD(0, Constants.kElevatorCargoD, Constants.kTimeoutMs);
        config_kF(0, Constants.kElevatorCargoF, Constants.kTimeoutMs);

        //Hatch Settings
        config_kP(1, Constants.kElevatorHatchP, Constants.kTimeoutMs);
        config_kI(1, Constants.kElevatorHatchI, Constants.kTimeoutMs);
        config_kD(1, Constants.kElevatorHatchD, Constants.kTimeoutMs);
        config_kF(1, Constants.kElevatorHatchF, Constants.kTimeoutMs);

    }

}