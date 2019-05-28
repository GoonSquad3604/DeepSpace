package frc.subsystem;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;

public class Sucker
{

    private WPI_TalonSRX sucker;
    private PowerDistributionPanel pdp;
    private Relay relay;

    /**Initializes a Sucker object
    *@param suckerID the ID of the sucker
    *@param relayID the ID of the removal relay
    */
    public Sucker(int suckerID, int relayID)
    {
        sucker = new WPI_TalonSRX(suckerID);
        pdp = new PowerDistributionPanel(0);
        relay = new Relay(relayID);
    }
    /**Initializes a Sucker object
    *@param suckerID the ID of the sucker
    */
    public Sucker(int suckerID)
    {
        this(suckerID,0);
    }

    /** Makes the sucker suck
     *  @param succ the succ
     */
    public void set(double suck)
    {
        sucker.set(suck);
    }

    /** gets the PDP current for this device
     *  @return current
     */
    public double getCurrent()
    {
        return sucker.getOutputCurrent();
    }

    /** Turns on the release Relay
     * 
     */
    public void relayOn()
    {
        System.out.println("on");
        relay.set(Value.kForward);
    }

    /** Turns off the release Relay
     * 
     */
    public void relayOff()
    {
        System.out.println("off");
        relay.set(Value.kOff);
    }
}