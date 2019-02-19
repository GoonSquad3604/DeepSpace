package frc.commands.subsystem.cargo;

import frc.commands.AutonCommand;
import frc.subsystem.CargoManipulator;

public class CmdMoveHinge implements AutonCommand
{
    private CargoManipulator cargo;
    private double amount;
    private double speed;
    public CmdMoveHinge(double iAmount, double iSpeed, CargoManipulator iCargo)
    {
        cargo = iCargo;
        amount = iAmount;
        speed = iSpeed;
    }
    
    @Override
    public boolean isFinished() 
    {
        return Math.abs(cargo.getHingeLocation()) >= Math.abs(amount);
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

    //The init method gets called right before the command begins executing.
    @Override
    public void init()
    {

    }
    
}