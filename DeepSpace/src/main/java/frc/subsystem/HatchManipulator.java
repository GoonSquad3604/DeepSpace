package frc.subsystem;

import frc.robot.Constants;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class HatchManipulator 
{

    private WPI_TalonSRX openClose, forwardBackwards;
    private boolean open = true;

    public HatchManipulator(int openCloseID, int forwardBackwardsID)
    {

        openClose = new WPI_TalonSRX(openCloseID);
        openClose.setInverted(true);
        forwardBackwards = new WPI_TalonSRX(forwardBackwardsID);
        forwardBackwards.setInverted(true);

    } 

    //Opens the beak
    public void runOpen()
    {
        //TODO
    }

    //Closes the beak
    public void runClose()
    {
        //TODO
    }

    //Tilts the manipulator?
    public void moveForwardBackwards(double amount)
    {
        //TODO
    }

    public boolean getOpen()
    {
        return open;
    }

    public void setOpen(boolean b)
    {
        this.open = b;
    }

    //@return the encoder position of the beak.
    public int getOpenCloseLocation()
    {
        return 0;
    }

}

