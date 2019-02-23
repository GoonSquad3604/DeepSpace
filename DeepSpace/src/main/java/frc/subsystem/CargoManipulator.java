package frc.subsystem;

import static frc.robot.Constants.*;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class CargoManipulator
{

    private WPI_TalonSRX intakeControl, hingeRight, hingeLeft; 
    /**
     *  sets int IDs for motors
     * @param intakeControlID sets ID cargo intake/output
     * @param hingeRightID sets ID hindge right motor
     * @param hingeLeftID sets ID hindge left motor
     */
    public CargoManipulator(int intakeControlID, int hingeRightID, int hingeLeftID)
    {

        intakeControl = new WPI_TalonSRX(intakeControlID);
        intakeControl.setInverted(true);
        hingeRight = new WPI_TalonSRX(hingeRightID);
        hingeRight.setInverted(true);
        hingeLeft = new WPI_TalonSRX(hingeLeftID);
        hingeLeft.setInverted(false);

    }

    // Intakes cargo
    public void runIntake()
    {

        intakeControl.set(1);
        
    }

    
    // Dispenses cargo
    public void runDispense()
    {

        intakeControl.set(-1);

    }
    // Intake/outputs set zero
    public void stop()
    {
        intakeControl.set(0);
    }

    /**
     * runs the Hindge
     * @param speed amount from -1 to 1
     */
    public void runHinge(double speed)
    {
        hingeRight.set(speed);
        hingeLeft.set(speed);
    }
    
    /**
     * gets the Hindge location
     * @return Hinge location
     */
    public double getHingeLocation()
    {
        return hingeRight.getSensorCollection().getAnalogInRaw();
    }

}