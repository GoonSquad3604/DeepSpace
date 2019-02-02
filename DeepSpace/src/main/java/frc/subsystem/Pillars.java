package frc.subsystem;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import frc.robot.Constants;

public class Pillars 
{

    private WPI_TalonSRX rightSide, leftSide;

    public Pillars(int rightSideID, int leftSideID)
    {
        rightSide = new WPI_TalonSRX(rightSideID);
        rightSide.setInverted(true);
        leftSide = new WPI_TalonSRX(leftSideID);
        leftSide.setInverted(true);
        
    }

}