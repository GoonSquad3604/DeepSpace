package frc.commands.subsystem;

import frc.commands.AutonCommand;
import frc.robot.Constants;
import frc.subsystem.HatchManipulator;

//If the beak is forward, this command will pull the beak.
//If the beak is backwoards, this command will push the beak.

public class CmdMoveBeak implements AutonCommand
{
 
    //A boolean (true/false value) that specifies whether or not the beak is opening or closing.
    private boolean forward = false;

    //The beak subsystem as an object.
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