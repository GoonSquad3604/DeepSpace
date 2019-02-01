package frc.subsystem;

import frc.robot.Constants;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class CargoManipulator{

    private WPI_TalonSRX intakeControlTop, intakeControlBottom, hinge; 

    public CargoManipulator(int intakeControlTopID, int intakeControlBottomID, int hingeID)
    {

        intakeControlTop = new WPI_TalonSRX(intakeControlTopID);
        intakeControlTop.setInverted(true);
        intakeControlBottom = new WPI_TalonSRX(intakeControlBottomID);
        intakeControlBottom.setInverted(true);
        hinge = new WPI_TalonSRX(hingeID);
        hinge.setInverted(true);

    }
    public void runIntake()
    {
        intakeControlTop.set(0.8);
        intakeControlBottom.set(0.8);
    }

    public void runDispense()
    {
        intakeControlTop.set(-0.8);
        intakeControlBottom.set(-0.8);
    }

}