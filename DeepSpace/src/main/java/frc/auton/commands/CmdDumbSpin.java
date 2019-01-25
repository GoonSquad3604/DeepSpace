package frc.auton.commands;

import edu.wpi.first.wpilibj.Timer;
import frc.subsystem.DriveTrain;

public class CmdDumbSpin implements AutonCommand
{
    //Spins the bot dumbly. !!!ONLY FOR USE IN TESTS!!!s
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
    public void runTask() {
        System.out.println(drive);
        drive.arcadeDrive(-0.0,0.6);
    }

    @Override
    public double getStatus() {
        return 0;
    }

    @Override
    public void init() {
    }

}