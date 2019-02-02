package frc.subsystem;

import frc.robot.Constants;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class HatchManipulator 
{
    private WPI_TalonSRX openClose, forwardBackwards;
    private boolean open = true;
    private boolean forward = true;

    public HatchManipulator(int openCloseID, int forwardBackwardsID)
    {
        openClose = new WPI_TalonSRX(openCloseID);
        openClose.setInverted(true);
        forwardBackwards = new WPI_TalonSRX(forwardBackwardsID);
        forwardBackwards.setInverted(true);
    } 

    //Opens the beak
    public void runOpen(double amount)
    {
        openClose.set(amount);

    }

    //Closes the beak
    public void runClose(double amount)
    {

        openClose.set(amount);

    }


    //pushes the manipulator forwards and pulls it back in back
    public void moveForwardBackwards(double amount)
    {

        forwardBackwards.set(amount);

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
    public double getOpenCloseLocation()
    {

        return 0;

    }

    public double getforwardBackwardsLocation()
    {

        return 0;

    }

	public boolean getForward() {
        
        return forward;

    }

    public void setForward(boolean f)
    {

        this.forward = f;

    }
   
}

