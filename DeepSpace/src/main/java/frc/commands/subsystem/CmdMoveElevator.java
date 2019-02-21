package frc.commands.subsystem;

import frc.commands.AutonCommand;
import static frc.robot.Constants.*;
import frc.subsystem.Elevator;

//Moves the elevator to a predefined height.
public class CmdMoveElevator implements AutonCommand
{
    private Elevator elevator;
    private double height;
    private boolean moved;

    public CmdMoveElevator(double iHeight, Elevator iElevator)
    {
        height = iHeight;
        moved = false;
        if(iElevator == null)
        {
            throw new NullPointerException("NO ELEVATOR FOUND!");
        }
        else
        {
            elevator = iElevator;
        }
        moved = false;
    }
    
    @Override
    public boolean isFinished() 
    {
        return (moved && (Math.abs(elevator.getElevator().getSelectedSensorVelocity()) < 100));
    }

    @Override
    public void end()
    {      
        elevator.setPower(0);
    }
    @Override
    public void runTask() 
    {
        elevator.moveElevator(height);
        if(Math.abs(elevator.getElevator().getSelectedSensorVelocity()) > 100)
        {
            moved = true;
        }
    }

    //Returns the height of the elevator. Can be used in a merge
    @Override
    public double getStatus() 
    {
        return height;
    }
    @Override
    public void init() 
    {
        
    }

}