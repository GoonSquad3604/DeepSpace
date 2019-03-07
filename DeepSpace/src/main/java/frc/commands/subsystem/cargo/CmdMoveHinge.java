package frc.commands.subsystem.cargo;

import frc.commands.AutonCommand;
import frc.subsystem.CargoManipulator;
import static frc.robot.Constants.*;

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
        return cargo.getHingeAngle() < (amount + kHingeError) && cargo.getHingeAngle() > (amount - kHingeError);
    }

    @Override
    public void runTask() 
    {
        if(cargo.getHingeAngle() > amount)
        {
            cargo.runHinge(speed);
        }
        else if(cargo.getHingeAngle() < amount)
        {
            cargo.runHinge(-speed);
        }
        else
        {
            cargo.runHinge(0);
        }
        
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