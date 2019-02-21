package frc.subsystem;

import static frc.robot.Constants.*;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class HatchManipulator 
{
    private WPI_TalonSRX hatch;
    private boolean open = true;
    private boolean forward = true;

    public HatchManipulator(int hatchID)
    {
        hatch = new WPI_TalonSRX(hatchID);
        hatch.setInverted(true);
    } 

    //Opens the hatch
    public void runHatch(double amount)
    {
        hatch.set(amount);
    }

    public int getLocation()
    {
        return hatch.getSelectedSensorPosition();
    }
    
}

