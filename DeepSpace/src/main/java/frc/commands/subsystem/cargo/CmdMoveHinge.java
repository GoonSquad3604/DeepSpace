package frc.commands.subsystem.cargo;

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
        return Math.abs(cargo.getHingeLeftLocation()) >= Math.abs(amount) && Math.abs(cargo.getHingeRightLocation()) >= Math.abs(amount);
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