package frc.subsystem;

import static frc.robot.Constants.*;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class HatchManipulator 
{
    private WPI_TalonSRX hatch;
    private boolean open = true;
    private boolean forward = true;

    /**
     * sets ID for hatch and inverts it
     * @param hatchID sets ID for the hatch manipulator
     */
    public HatchManipulator(int hatchID)
    {
        hatch = new WPI_TalonSRX(hatchID);
        hatch.setInverted(true);
    } 

    /**
     * Opens the hatch
     * @param speed sets motor 1 to -1
     */
    public void runHatch(double speed)
    {
        hatch.set(speed);
    }
    /**
     * gets the location of sensor
     * @return location of the sensor
     */
    public int getLocation()
    {
        return hatch.getSelectedSensorPosition();
    }
    
}

