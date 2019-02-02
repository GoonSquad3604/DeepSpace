package frc.subsystem;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.Constants;

public class Pillars 
{

    private WPI_TalonSRX rightSide, leftSide, wheels;

    public Pillars(int rightSideID, int leftSideID, int wheelsID)
    {
        rightSide = new WPI_TalonSRX(rightSideID);
        rightSide.setInverted(true);
        leftSide = new WPI_TalonSRX(leftSideID);
        leftSide.setInverted(true);
        wheels = new WPI_TalonSRX(wheelsID);
        wheels.setInverted(false);
    }

    //Moves the wheels to a specific distance.
    public void moveWheels(double distance)
    {
        //TODO
    }
    //Sets the pillars themselves to a specific height.
    public void setPillars(double height)
    {
        //TODO
    }

    //@return the distance the pillars have travelled.
    public double getDistance()
    {
        //TODO
        return 0;
    }

    //@return the height of the pillars 
    public double getHeight()
    {
        //TODO
        return 0;
    }

    //Resets the pillar subsystem.
    public void reset()
    {
        //TODO
    }

}