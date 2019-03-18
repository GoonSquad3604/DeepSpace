package frc.subsystem;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class HatchManipulator 
{
    private WPI_TalonSRX articulator;
    private ArticulatorState articulatorState;
    /**
     * sets ID for hatch and inverts it
     * @param hatchID sets ID for the hatch articulator
     */
    public HatchManipulator(int hatchID)
    {
        articulator = new WPI_TalonSRX(hatchID);
        articulator.setInverted(false);
        articulatorState = ArticulatorState.kIn;
    } 


    /**
     * Moves the articulator
     * @param speed sets motor of articulator
     */
    public void runArticulator(double speed)
    {
        articulator.set(speed);
    }


    
    /**
     * Gets the current state of the articulator.
     * @return the articulator's state
     */
    public ArticulatorState getState()
    {
        return articulatorState;
    }

    
    /**
     * Gets the current location of the articulator.
     * @return the articulator's location
     */
    public int getLocation()
    {
        return -articulator.getSelectedSensorPosition();
    }
    
    /**
     * Sets the articulator's state
     * @param state the state to set the articulator to
     */
    public void setState(ArticulatorState state)
    {
        articulatorState = state;
    }
}

