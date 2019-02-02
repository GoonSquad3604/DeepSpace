package frc.subsystem;

import frc.robot.Constants;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class HatchManipulator 
{

    private WPI_TalonSRX openClose, forwardBackwords;
    private boolean open = true;

    public HatchManipulator(int openCloseID, int forwardBackwordsID)
    {

        openClose = new WPI_TalonSRX(openCloseID);
        openClose.setInverted(true);
        forwardBackwords = new WPI_TalonSRX(forwardBackwordsID);
        forwardBackwords.setInverted(true);

    } 

    public void runOpen()
    {

        openClose.set(0);

    }

    public void runClose()
    {

        openClose.set(0);

    }

    public boolean getOpen()
    {
        return open;
    }

    public void setOpen(boolean b)
    {
        this.open = b;
    }
    
    public int getOpenCloseLocation()
    {
        return openClose.getSelectedSensorPosition();
    }

}

