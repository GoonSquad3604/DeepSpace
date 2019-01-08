package frc.subsystem;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class Elevator extends WPI_TalonSRX{

    public Elevator(int motorID){
        super(motorID);
    }

}