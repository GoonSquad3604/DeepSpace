package frc.subsystem;

import frc.robot.Constants;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class HatchManipulator {

    private WPI_TalonSRX openClose, forwardBackwards;

    public HatchManipulator(int openCloseID, int forwardBackwordsID)
    {

        openClose = new WPI_TalonSRX(openCloseID);
        openClose.setInverted(true);
        forwardBackwards = new WPI_TalonSRX(forwardBackwordsID);
        forwardBackwards.setInverted(true);

    } 
    public void runOpen()
    {

        openClose.set(0);

    }

    public void runClose()
    {

        openClose.set(0);

    }

}

