package frc.commands.special;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.auton.Auton;
import frc.auton.DumbAuton;
import frc.auton.TestAuton;
import frc.commands.AutonCommand;
import frc.subsystem.drivetrain.*;

public class CmdTeleop implements AutonCommand{

    //Runs when there is no command. Does Teleoperated Stuffs.
    private DriveTrain drive;
    private XboxController driveStick;
    private XboxController operateStick;
    private Auton auton;
    private boolean running;
    public CmdTeleop(DriveTrain drive, XboxController driveStick, XboxController operateStick, Auton auton)
    {
        this.driveStick = driveStick;
        this.operateStick = operateStick;
        this.drive = drive;
        this.auton = auton;
    }
    @Override
    public boolean isFinished() 
    {
        return false;
    }

    @Override
    public void runTask() 
    {
        drive.arcadeDrive(driveStick.getRawAxis(1),-driveStick.getRawAxis(4));
        if(auton.getSize()==0)
        {
            if(driveStick.getAButtonPressed() && running)
            {
                DumbAuton.addCommands(this.auton);
                this.running = false;
                this.end();
                System.out.println("added to queue!");
            }
            if(driveStick.getBButtonPressed() && running)
            {
                TestAuton.addCommands(this.auton);
                this.running = false;
                this.end();
                System.out.println("added to queue!");
            }
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
        this.running = true;
        /*ALL BUTTONS that run auton commands MUST be checked here.
        Without this, Autons may start from accidental button pushes!*/
        driveStick.getAButtonPressed();
        driveStick.getBButtonPressed();
        driveStick.getXButtonPressed();
        driveStick.getYButtonPressed();
    }
    public boolean getRunning()
    {
        return running;
    }
    @Override
    public void end()
    {
        drive.setLeftPosition(0);
        drive.setRightPosition(0);
    }
}
