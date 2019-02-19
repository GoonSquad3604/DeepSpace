package frc.commands.subsystem.hatch;

import frc.commands.AutonCommand;
import static frc.robot.Constants.*;
import frc.subsystem.HatchManipulator;

//If the blackLotus is open, this command will close the blackLotus.
//If the blackLotus is closed, this command will open the blackLotus.
public class CmdToggleLotus implements AutonCommand
{
    //The blackLotus subsystem as an object.
    private HatchManipulator blackLotus;
    //A boolean (true/false value) that specifies whether or not the blackLotus is opening or closing.
    private boolean opening = false;

    /**This is the constructor.
     * The constructor is the method that "sets up" this object.
     * It takes in all subsystems as parameters, as well as any extra values.
     */
    public CmdToggleLotus(HatchManipulator blackLotus)
    {
        //Throws an error when the blackLotus is not found.
        if(blackLotus == null)
        {
            throw new NullPointerException("BEAK NOT FOUND!");
        }
        //Officially makes the blackLotus object usable.
        this.blackLotus = blackLotus;
    }
    
    @Override
    public boolean isFinished() 
    {
        //If the blackLotus is opening, will return true when the blackLotus is open.
        //If the blackLotus is closing, will return true when the blackLotus is closed.
        return(opening && blackLotus.getOpenCloseLocation() > kOpenHatch)
        ||(!opening && blackLotus.getOpenCloseLocation() < kClosedHatch);
    }

    @Override
    public void runTask() 
    {
        if(opening)
        {
            blackLotus.runOpen(0.8); // Opens the blackLotus if it started closed.
        }
        else
        {
            blackLotus.runClose(0.8);// Closes the blackLotus if it started open.
        }
    }

    //Returns a status. This is useful in merging commands. This specific command doesn't return a status (yet).
    @Override
    public double getStatus() 
    {
        return 0;
    }

    //The init method gets called right before the command begins executing.
    @Override
    public void init() 
    {
        //Sets the opening value to the OPPOSITE (!) of the blackLotus's current status.
        //ie. If the blackLotus is already open, "opening" is set to false.
        opening = !blackLotus.getOpen();
    }
    
    @Override
    public void end()
    {
        blackLotus.setOpen(opening);  //Updates the current position of the blackLotus.
    }
}