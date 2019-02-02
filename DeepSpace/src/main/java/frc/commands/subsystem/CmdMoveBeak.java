package frc.commands.subsystem;

import frc.commands.AutonCommand;
import frc.robot.Constants;
import frc.subsystem.HatchManipulator;

public class CmdMoveBeak implements AutonCommand
{

    private boolean forward = false;
    private HatchManipulator beak;

    public CmdMoveBeak(HatchManipulator beak )
    {

        if(beak == null)
        {
            throw new NullPointerException("BEAK NOT FOUND!");
        }


        this.beak = beak;
    }

    @Override
    public boolean isFinished() {
         return(forward && beak.getforwardBackwardsLocation() > Constants.KForwardBeak)
        ||(!forward && beak.getforwardBackwardsLocation() < Constants.KBackwardBeak);

    }

    @Override
    public void runTask() {

        if(forward)
        {
            
            beak.moveForwardBackwards(0.8); 

        }
        else
        {

            beak.moveForwardBackwards(-0.8);

        }

    }

    @Override
    public double getStatus() {

        return 0;

    }

    @Override
    public void init() 
    {
        forward = !beak.getForward();
    }
    @Override
    public void end()
    {
        beak.setForward(!beak.getForward());
    }

}