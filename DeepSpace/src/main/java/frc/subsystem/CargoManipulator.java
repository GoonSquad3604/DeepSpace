package frc.subsystem;

import frc.robot.Constants;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
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
        hingeLeft = new WPI_TalonSRX(hingeRightID);
        hingeLeft.setInverted(true);

    }

    //Intakes cargo
    public void runIntake()
    {

        intakeControl.set(0.8);
        
    }

    //Dispenses cargo
    public void runDispense()
    {

        intakeControl.set(-0.8);

    }

    //TODO Hinge-related stuff. Methods still need to be made.

public void runHingeIntake(double amount)
{

        hingeRight.set(amount);
        hingeLeft.set(amount);

}

public void runHingeSationary(double amount)
{

        hingeRight.set(amount);
        hingeLeft.set(amount);

}

    public double getHingelocation()
    {

        return 0;

    }

}