package frc.commands.subsystem;

import frc.commands.AutonCommand;
import frc.subsystem.Elevator;

public class CmdResetElevator implements AutonCommand
{
    private Elevator elevator;
    public CmdResetElevator(Elevator iElevator)
    {
        elevator = iElevator;
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
        elevator.setPower(0);
    }
}