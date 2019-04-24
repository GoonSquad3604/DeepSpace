package frc.commands.subsystem;

import frc.auton.Auton;
import frc.commands.AutonCommand;
import frc.subsystem.Elevator;

public class CmdResetElevator implements AutonCommand
{
    private Elevator elevator;
    private Auton auton;

    public CmdResetElevator(Elevator iElevator, Auton iAuton)
    {
        auton = iAuton;
        elevator = iElevator;
        auton.setIsElevatorCommand(true);
    }

    @Override
    public boolean isFinished() 
    {
        return elevator.getLimit();
    }

    @Override
    public void runTask() 
    {
        elevator.setPower(-0.2);
        elevator.setHeight(0);
    }

    @Override
    public double getStatus() 
    {
        return elevator.getLimit() ? 1 : 0;
    }

    @Override
    public void init() 
    {

    }
    @Override
    public void end()
    {
        auton.setIsElevatorCommand(false);
        elevator.setPower(0);
    }
}