package frc.commands.subsystem;

import frc.commands.AutonCommand;
import frc.subsystem.CargoManipulator;

public class CmdMoveHinge implements AutonCommand
{
    private CargoManipulator cargo;
    private double amount;
    private double speed;
    public CmdMoveHinge(double amount, double speed, CargoManipulator cargo)
    {
        this.cargo = cargo;
        this.amount = amount;
        this.speed = speed;
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

    @Override
    public void init()
    {

    }
    
}