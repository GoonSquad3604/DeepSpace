package frc.commands.subsystem.hatch;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import frc.auton.Auton;
import frc.commands.AutonCommand;
import static frc.robot.Constants.*;

public class CmdSuck implements AutonCommand
{

    private Auton auton;
    private double current;
    private Timer time;

    public CmdSuck(Auton iAuton)
    {
        time = new Timer();
        auton = iAuton;
        time.start();
        time.reset();
    }

    @Override
    public boolean isFinished() 
    {
        return auton.getOperateStick().getStickButtonPressed(Hand.kRight);
    }

    @Override
    public void runTask() 
    {
        
        current = auton.getSucker().getCurrent();
        
        System.out.println(current);

        if(current >= kMinHatchCurrent && current <= kMaxHatchCurrent)
        {
            if(time.get() > 0.25)
            {
                auton.getHatchManipulator().setHatch(true);
            }
        }
        else
        {
            time.reset();
        }
        
        auton.getSucker().set(auton.getHatchManipulator().getHatch() ? 0.2 : 0.65);
        

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
        auton.getSucker().set(0);
        auton.getHatchManipulator().setHatch(false);
    }



}