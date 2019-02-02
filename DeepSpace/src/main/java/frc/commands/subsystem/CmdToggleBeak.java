package frc.commands.subsystem;

import frc.commands.AutonCommand;
import frc.robot.Constants;
import frc.subsystem.HatchManipulator;

//If the beak is open, this command will close the beak.
//If the beak is closed, this command will open the beak.
public class CmdToggleBeak implements AutonCommand
{
    HatchManipulator beak;
    boolean opening = false;

    public CmdToggleBeak(HatchManipulator beak)
    {
        
        if(beak == null)
        {
            throw new NullPointerException("BEAK NOT FOUND!");
        }
        this.beak = beak;
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
        // Opens the beak if it started closed.
        //Closes the beak if it started open.
        if(opening)
        {
            beak.runOpen();
        }
        else
        {
            beak.runClose();
        }
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
}