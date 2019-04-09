package frc.commands.subsystem.hatch;
import frc.commands.AutonCommand;
import static frc.robot.Constants.*;

import frc.auton.Auton;
import frc.subsystem.HatchManipulator;
import frc.subsystem.ArticulatorState;
public class CmdSetHatch implements AutonCommand
{
    private HatchManipulator hatchManipulator;
    private ArticulatorState state;
    private Auton auton;
    
    public CmdSetHatch(HatchManipulator iManipulator, ArticulatorState iState, Auton iAuton)
    {
        hatchManipulator = iManipulator;
        state = iState;
        auton = iAuton;
    }
    
    @Override
    public boolean isFinished() 
    {
        if(state == ArticulatorState.kOut)
        {
            return hatchManipulator.getLocation() >= kArticulatorOut;
        }
        else if(state == ArticulatorState.kIn)
        {
            return hatchManipulator.getLocation() <= kArticulatorIn;
        }
        else if(state == ArticulatorState.kCargoShip)
        {
            return hatchManipulator.getLocation() >= kArticulatorCargoShip;
        }
        else
        {
            return hatchManipulator.getLocation() <= kArticulatorHatch;
        }
    }

    @Override
    public void runTask() 
    {
        auton.setIsHatchCommand(true);
        hatchManipulator.runArticulator(state == ArticulatorState.kIn || state == ArticulatorState.kHatch  ? -1 : 1);
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

    @Override
    public void end()
    {
        hatchManipulator.setState(state);
        auton.setIsHatchCommand(false);
    }

}