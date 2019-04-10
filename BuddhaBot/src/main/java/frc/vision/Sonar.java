package frc.vision;

import edu.wpi.first.wpilibj.AnalogInput;

public class Sonar extends AnalogInput
{
    public Sonar(int pin)
    {
        super(pin);
    }
    
    /**
     * Gets the sensor position in <i>inches</i>
     * @return sensor position in inches as a <code>double</code>
     */
    public double getInches()
    {
        double sensorValue = this.getVoltage();
        System.out.println("voltage:" + sensorValue);
        double voltage = sensorValue * (4880 / 1023.0);
        return sensorValue <= 58 ? -1 : (voltage)*25.4;
    }
    /**
     * Gets the sensor position in <i>centimeters</i>
     * @return sensor position in centimeters as a <code>double</code>
     */
    public double getCentimeters()
    {
        return getInches() == -1 ? -1 : getInches() / 2.54;
    }
}