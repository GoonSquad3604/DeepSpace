package frc.subsystem;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.Constants;

public class Pillars 
{

    private WPI_TalonSRX  front, back, pillerWheels;

    public Pillars(int frontID, int backID, int pillerWheelsID)
    {
        front = new WPI_TalonSRX(frontID);
        front.setInverted(true);
        back = new WPI_TalonSRX(backID);
        back.setInverted(true);
        pillerWheels = new WPI_TalonSRX(pillerWheelsID);
        pillerWheels.setInverted(true);
        
    }

}