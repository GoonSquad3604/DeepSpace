package frc.commands.drive;

import frc.commands.AutonCommand;
import frc.subsystem.drivetrain.*;

public class CmdDumbSpin implements AutonCommand
{
    //Spins the bot dumbly. !!!ONLY FOR USE IN TESTS!!!
    private DriveTrain drive;
    
    public CmdDumbSpin(DriveTrain drive)
    {
        this.drive = drive;
    }
    
    @Override
    public boolean isFinished() 
    {
        return false;
    }

    @Override
    public void runTask() 
    {
        drive.arcadeDrive(-0.0, 0.6);
    }

    @Override
    public double getStatus() {
        return 0;
    }

    @Override
    public void init() {
    }

}