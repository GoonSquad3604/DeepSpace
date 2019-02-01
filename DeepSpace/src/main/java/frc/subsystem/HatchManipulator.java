package frc.subsystem;

import frc.robot.Constants;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class HatchManipulator {

    private TalonSRX openClose, forwardBackwords;

    public HatchManipulator(int openCloseID, int forwardBackwordsID)
    {


        openClose = new TalonSRX(openCloseID);
        openClose.setInverted(true);
        forwardBackwords = new TalonSRX(forwardBackwordsID);
        forwardBackwords.setInverted(true);
        
    } 
}

