package frc.subsystem;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import frc.robot.Constants;

public class Pillars {

    private TalonSRX rightSide, leftSide;

    public Pillars(int rightSideID, int leftSideID)
    {
        rightSide = new TalonSRX(rightSideID);
        rightSide.setInverted(true);
        leftSide = new TalonSRX(leftSideID);
        leftSide.setInverted(true);
        
    }

}