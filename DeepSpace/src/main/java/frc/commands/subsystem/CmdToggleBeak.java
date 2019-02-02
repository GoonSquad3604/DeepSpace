package frc.commands.subsystem;

import frc.commands.AutonCommand;
import frc.robot.Constants;
import frc.subsystem.HatchManipulator;

//If the beak is open, this command will close the beak.
//If the beak is closed, this command will open the beak.
public class CmdToggleBeak implements AutonCommand
{
    //The beak subsystem as an object.
    HatchManipulator beak;
    //A boolean (true/false value) that specifies whether or not the beak is opening or closing.
    boolean opening = false;

    /**This is the constructor.
     * The constructor is the method that "sets up" this object.
     * It takes in all subsystems as parameters, as well as any extra values.
     */
    public CmdToggleBeak(HatchManipulator beak)
    {
        //Throws an error when the beak is not found.
        if(beak == null)
        {
            throw new NullPointerException("BEAK NOT FOUND!");
        }
        //Officially makes the beak object usable.
        this.beak = beak;
        //Sets the opening value to the OPPOSITE (!) of the beak's current status.
        //ie. If the beak is already open, "opening" is set to false.
        opening = !beak.getOpen();
    }
    
    @Override
    public boolean isFinished() 
    {
        //If the beak is opening, will return true when the beak is open.
        //If the beak is closing, will return true when the beak is closed.
        return(opening && beak.getOpenCloseLocation() > Constants.kOpenBeak)
        ||(!opening && beak.getOpenCloseLocation() < Constants.kClosedBeak);
    }

    @Override
    public void runTask() 
    {
        if(opening)
        {
            beak.runOpen(); // Opens the beak if it started closed.
        }
        else
        {
            beak.runClose();// Closes the beak if it started open.
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

    }
}