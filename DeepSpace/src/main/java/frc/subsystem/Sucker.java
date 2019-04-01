package frc.subsystem;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.PowerDistributionPanel;

public class Sucker
{

    private WPI_TalonSRX sucker;
    private PowerDistributionPanel pdp;

    public Sucker(int suckerID)
    {
        sucker = new WPI_TalonSRX(suckerID);
        pdp = new PowerDistributionPanel(0);
    }

    public void set(double suck)
    {
        sucker.set(suck);
    }

    public double getCurrent()
    {
        return pdp.getCurrent(sucker.getDeviceID());
    }


}