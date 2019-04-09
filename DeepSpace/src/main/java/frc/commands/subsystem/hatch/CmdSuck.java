package frc.commands.subsystem.hatch;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import frc.auton.Auton;
import frc.commands.AutonCommand;
import static frc.robot.Constants.*;

import javax.lang.model.util.ElementScanner6;

public class CmdSuck implements AutonCommand
{

    private boolean timerStarted = false;
    private Auton auton;
    private double current;
    private Timer waitTime;
    private Timer verifyTime;

    public CmdSuck(Auton iAuton)
    {
        waitTime = new Timer();
        verifyTime = new Timer();
        auton = iAuton;
    }

    @Override
    public boolean isFinished() 
    {
        auton.getSucker().set(auton.getHatchManipulator().getHatch() ? 0.2 : 1);
        return auton.getHatchManipulator().getHatch();
    }

    @Override
    public void runTask() 
    {
        //System.out.println(time.get());
        current = auton.getSucker().getCurrent();
        
        if(!timerStarted)
        {
            waitTime.start();
            waitTime.reset();
            verifyTime.start();
            verifyTime.reset();
            timerStarted = true;
        }

        if(waitTime.get() > 1)
        {
            if(current >= kMinHatchCurrent && current <= kMaxHatchCurrent)
            {
                if(verifyTime.get() > 0.5)
                {
                    auton.getHatchManipulator().setHatch(true); 
                }
                
            }
            else
            {
                verifyTime.reset();
            }
        }
        else
        {
            verifyTime.reset();
        }
        
        if(auton.getOperateStick().getPOV() == kDpadUp)
        {
            auton.getHatchManipulator().setHatch(true);
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

    @Override
    public void end() {
    }



}