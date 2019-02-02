package frc.commands.subsystem;

import frc.commands.AutonCommand;
import frc.robot.Constants;
import frc.subsystem.Elevator;

public class CmdMoveElevator implements AutonCommand
{
    //Moves the elevator to a predefined height.
    Elevator elevator;
    double height;

    public CmdMoveElevator(double height, Elevator elevator)
    {
        this.height = height;
        if(elevator == null)
        {
            throw new NullPointerException("NO ELEVATOR FOUND!");
        }
        else
        {
            this.elevator = elevator;
        }
    }
    
    @Override
    public boolean isFinished() 
    {
        return elevator.getHeight() > height-Constants.kElevatorError
        && elevator.getHeight() < height+Constants.kElevatorError;
    }

    @Override
    public void runTask() 
    {
        elevator.moveElevator(height);
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