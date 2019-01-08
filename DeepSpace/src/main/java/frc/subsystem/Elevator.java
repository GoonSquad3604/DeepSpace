package frc.subsystem;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.Constants;

public class Elevator extends WPI_TalonSRX{

    public Elevator(int elevatorID){
        
        super(elevatorID);
        
        this.config_kP(0, Constants.kElevatorP, Constants.kTimeoutMs);
        this.config_kI(0, Constants.kElevatorI, Constants.kTimeoutMs);
        this.config_kD(0, Constants.kElevatorD, Constants.kTimeoutMs);
        this.config_kF(0, Constants.kElevatorF, Constants.kTimeoutMs);
    
    }

}