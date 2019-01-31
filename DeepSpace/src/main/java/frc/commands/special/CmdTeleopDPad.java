package frc.commands.special;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import frc.auton.Auton;
import frc.auton.DumbAuton;
import frc.auton.LockOnAuton;
import frc.auton.TestAuton;
import frc.commands.AutonCommand;
import frc.subsystem.DriveTrain;

public class CmdTeleopDPad implements AutonCommand{

    //Runs when there is no command. Does Teleoperated Stuffs.
    private DriveTrain drive;
    private XboxController driveStick;
    private XboxController operateStick;
    private Auton auton;
    private boolean running;
    public CmdTeleopDPad(DriveTrain drive, XboxController driveStick, XboxController operateStick, Auton auton)
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
        if(driveStick.getPOV()==-1)
        {
            drive.setDrive(0);
            drive.setTurn(0);
        }
        else if(driveStick.getPOV()==0)
        {
            drive.setDrive(-1);
            drive.setTurn(0);
        }
        else if(driveStick.getPOV()==45)
        {
            drive.setDrive(-0.6);
            drive.setTurn(-0.6);
        }
        else if(driveStick.getPOV()==90)
        {
            drive.setDrive(0);
            drive.setTurn(-1);
        }
        else if(driveStick.getPOV()==135)
        {
            drive.setDrive(0.6);
            drive.setTurn(-0.6);
        }
        else if(driveStick.getPOV()==180)
        {
            drive.setDrive(1);
            drive.setTurn(0);
        }
        else if(driveStick.getPOV()==225)
        {
            drive.setDrive(0.6);
            drive.setTurn(0.6);
        }
        else if(driveStick.getPOV()==270)
        {
            drive.setDrive(0.0);
            drive.setTurn(1.0);
        }
        else if(driveStick.getPOV()==315)
        {
            drive.setDrive(-0.6);
            drive.setTurn(0.6);
        }
        drive.arcadeDrive();
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
            if(driveStick.getBumper(Hand.kLeft) && running)
            {
                
                LockOnAuton.addCommands(this.auton);
                this.running = false;
                this.end();
                auton.initAuton();
                System.out.println("added to queue! iygutdyetrdytrduurrr");
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
        drive.getLeftMotor().setSelectedSensorPosition(0);
        drive.getRightMotor().setSelectedSensorPosition(0);
    }
}
