package frc.commands.subsystem.hatch;
import frc.commands.AutonCommand;
import static frc.robot.Constants.*;

import frc.auton.Auton;
import frc.subsystem.HatchManipulator;
import frc.subsystem.ArticulatorState;
public class CmdToggleHatch implements AutonCommand
{
    private HatchManipulator hatchManipulator;
    private boolean in;
    private Auton auton;

    public CmdToggleHatch(HatchManipulator iManipulator, Auton iAuton)
    {
        hatchManipulator = iManipulator;
        in = hatchManipulator.getState() == ArticulatorState.kIn;
        auton = iAuton;
    }
    
    @Override
    public boolean isFinished() 
    {
        auton.setIsHatchCommand(true);
        
        if(in)
        {
            return hatchManipulator.getLocation() >= kArticulatorOut;
        }
        else
        {
            return hatchManipulator.getLocation() <= kArticulatorIn;
        }
    }

    @Override
    public void runTask() {
        hatchManipulator.runArticulator(in ? 1 : -1);
    }

    @Override
    public double getStatus() 
    {
        return 0;
    }

    @Override
    public void init() 
    {
        in = hatchManipulator.getState() == ArticulatorState.kIn;
    }

    @Override
    public void end()
    {
        auton.setIsHatchCommand(false);
        
        if(in)
        {
            hatchManipulator.setState(ArticulatorState.kOut);
        }
        else
        {
            hatchManipulator.setState(ArticulatorState.kIn);
        }
    }

}