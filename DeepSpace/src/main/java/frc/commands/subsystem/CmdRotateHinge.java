package frc.commands.subsystem;

import frc.commands.AutonCommand;
import frc.subsystem.CargoManipulator;

public class CmdRotateHinge implements AutonCommand
{
    CargoManipulator cargo;
    double voltage;
    double startVoltage;
    double speed;
    public CmdRotateHinge(double iVoltage, double iSpeed, CargoManipulator iCargo)
    {
        voltage = iVoltage;
        cargo = iCargo;
        startVoltage = cargo.getHingeLocation();
        speed = iSpeed;
    }
    @Override
    public boolean isFinished() 
    {
        return (speed < 0 && cargo.getHingeLocation() < voltage) 
            || (speed > 0 && cargo.getHingeLocation() > voltage);
    }

    @Override
    public void runTask() 
    {
        cargo.runHinge(speed);
    }

    @Override
    public double getStatus() 
    {
        return 0;
    }

    @Override
    public void init() 
    {

    }

} 