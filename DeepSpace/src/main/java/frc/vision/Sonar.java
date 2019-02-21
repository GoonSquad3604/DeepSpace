package frc.vision;

import edu.wpi.first.wpilibj.AnalogInput;
public class Sonar extends AnalogInput
{
    public Sonar(int pin)
    {
        super(pin);
    }
    public double getInches()
    {
        return (this.getVoltage()*118);
    }
}