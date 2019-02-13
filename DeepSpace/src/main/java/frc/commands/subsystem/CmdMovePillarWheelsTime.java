package frc.commands.subsystem;

import edu.wpi.first.wpilibj.Timer;
import frc.commands.AutonCommand;
import frc.subsystem.Pillars;

public class CmdMovePillarWheelsTime implements AutonCommand
{

    private Pillars pillars;
    private double seconds;
    private final double speed;
    private Timer timer;
    public CmdMovePillarWheelsTime(double iSeconds, double iSpeed, Pillars iPillars)
    {
        if(iPillars == null)
        {
            throw new NullPointerException("PILLARS NOT FOUND");
        }
        pillars = iPillars;
        seconds = iSeconds;
        speed = iSpeed;
        timer = new Timer();
    }
    @Override
    public boolean isFinished() 
    {
        return timer.get() >= seconds;
    }

    @Override
    public void runTask() 
    {
        pillars.moveWheels(speed);
    }

    @Override
    public double getStatus() 
    {
        return pillars.getDistance();
    }

    @Override
    public void init() 
    {
        timer.reset();
        timer.start();
    }
    @Override
    public void end()
    {
        pillars.moveWheels(0);
    }

}