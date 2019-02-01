package frc.subsystem;

import frc.robot.Constants;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class CargoManipulator{

    private TalonSRX intakeControlTop, intakeControlBottom, hinge; 

    public CargoManipulator(int intakeControlTopID, int intakeControlBottomID, int hingeID)
    {

        intakeControlTop = new TalonSRX(intakeControlTopID);
        intakeControlTop.setInverted(true);
        intakeControlBottom = new TalonSRX(intakeControlBottomID);
        intakeControlBottom.setInverted(true);
        hinge = new TalonSRX(hingeID);
        hinge.setInverted(true);
        
    }


}