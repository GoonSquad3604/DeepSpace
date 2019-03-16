package frc.commands.subsystem.hatch;
import frc.commands.AutonCommand;
import static frc.robot.Constants.*;
import frc.subsystem.HatchManipulator;
import frc.subsystem.ArticulatorState;
public class CmdToggleHatch implements AutonCommand
{
    private HatchManipulator hatchManipulator;
    private boolean in;
    public CmdToggleHatch(HatchManipulator iManipulator)
    {
        hatchManipulator = iManipulator;
    }
    @Override
    public boolean isFinished() {
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
        hatchManipulator.runArticulator(in ? 0.5 : -0.5);
    }

    @Override
    public double getStatus() {
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