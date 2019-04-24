package frc.commands.subsystem;

import frc.commands.AutonCommand;
import static frc.robot.Constants.*;

import frc.auton.Auton;
import frc.subsystem.Elevator;

//Moves the elevator to a predefined height.
public class CmdMoveElevator implements AutonCommand
{
    private Elevator elevator;
    private double height;
    private boolean moved;
    private Auton auton;
    
    public CmdMoveElevator(double iHeight, Elevator iElevator, Auton iAuton)
    {
        auton = iAuton;
        height = iHeight;
        moved = false;
        if(iElevator == null)
        {
            throw new NullPointerException("NO ELEVATOR FOUND!");
        }
        else
        {
            auton.setIsElevatorCommand(true);
            elevator = iElevator;
        }
    }
    
    @Override
    public boolean isFinished() 
    {
        return (moved && (Math.abs(elevator.getElevator().getSelectedSensorVelocity()) < 100));
    }

    @Override
    public void end()
    {      
        auton.setIsElevatorCommand(false);
        elevator.setPower(0);
    }

    @Override
    public void runTask() 
    {
        System.out.println("Moving");
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