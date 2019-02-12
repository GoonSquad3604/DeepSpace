package frc.commands.subsystem;

import frc.commands.AutonCommand;
import frc.robot.Constants;
import frc.subsystem.HatchManipulator;

//If the blackLotus is forward, this command will pull the blackLotus.
//If the blackLotus is backwoards, this command will push the blackLotus.

public class CmdMoveLotus implements AutonCommand
{
 
    //A boolean (true/false value) that specifies whether or not the blackLotus is opening or closing.
    private boolean forward = false;

    //The blackLotus subsystem as an object.
    private HatchManipulator blackLotus;

    public CmdMoveLotus(HatchManipulator blackLotus )
    {

        if(blackLotus == null)
        {
            throw new NullPointerException("BLACK LOTUS NOT FOUND!");
        }


        this.blackLotus = blackLotus;
    }

    @Override
    public boolean isFinished() {
         return(forward && blackLotus.getforwardBackwardsLocation() > Constants.KForwardLotus)
        ||(!forward && blackLotus.getforwardBackwardsLocation() < Constants.KBackwardLotus);

    }

    @Override
    public void runTask() {

        if(forward)
        {
            
            blackLotus.moveForwardBackwards(0.8); 

        }
        else
        {

            blackLotus.moveForwardBackwards(-0.8);

        }

    }

    @Override
    public double getStatus() {

        return 0;

    }

    @Override
    public void init() 
    {
        forward = !blackLotus.getForward();
    }
    @Override
    public void end()
    {
        blackLotus.setForward(!blackLotus.getForward());
    }

}