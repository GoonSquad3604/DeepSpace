package frc.subsystem;

import static frc.robot.Constants.*;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class CargoManipulator
{

    private WPI_TalonSRX intakeControl, hingeRight, hingeLeft; 

    public CargoManipulator(int intakeControlID, int hingeRightID, int hingeLeftID)
    {

        intakeControl = new WPI_TalonSRX(intakeControlID);
        intakeControl.setInverted(true);
        hingeRight = new WPI_TalonSRX(hingeRightID);
        hingeRight.setInverted(true);
        hingeLeft = new WPI_TalonSRX(hingeLeftID);
        hingeLeft.setInverted(false);

    }

    //Intakes cargo
    public void runIntake()
    {

        intakeControl.set(1);
        
    }

    //Dispenses cargo
    public void runDispense()
    {

        intakeControl.set(-1);

    }

    public void stop()
    {
        intakeControl.set(0);
    }
    //TODO Hinge-related stuff. Methods still need to be made.

    public void runHinge(double amount)
    {

        hingeRight.set(amount);
        hingeLeft.set(amount);

    }
    

    public double getHingeLocation()
    {
        return hingeRight.getSensorCollection().getAnalogInRaw();
    }

}