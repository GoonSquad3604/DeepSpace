package frc.commands.subsystem.hatch;
import frc.commands.AutonCommand;
import static frc.robot.Constants.*;
import frc.subsystem.HatchManipulator;
import frc.subsystem.ArticulatorState;
public class CmdMovePickup implements AutonCommand
{
    private HatchManipulator hatchManipulator;
    
    public CmdMovePickup(HatchManipulator iManipulator)
    {
        hatchManipulator = iManipulator;
    }
    
    @Override
    public boolean isFinished() {
        
        return hatchManipulator.getLocation() >= 70;
    }

    @Override
    public void runTask() {
        hatchManipulator.runArticulator(1);
    }

    @Override
    public double getStatus() {
        return 0;
    }

    @Override
    public void init() 
    {

    }

    @Override
    public void end()
    {
        hatchManipulator.runArticulator(0);
    }

}