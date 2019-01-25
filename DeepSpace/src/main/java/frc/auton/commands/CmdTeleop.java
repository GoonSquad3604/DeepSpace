package frc.auton.commands;

import edu.wpi.first.wpilibj.XboxController;
import frc.auton.Auton;
import frc.auton.DumbAuton;
import frc.auton.TestAuton;
import frc.subsystem.DriveTrain;

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
        if(driveStick.getAButtonPressed() && running)
        {
            DumbAuton.addCommands(this.auton);
            this.running = false;
            this.end();
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
    }
    @Override
    public void end()
    {
        drive.getLeftMotor().setSelectedSensorPosition(0);
        drive.getRightMotor().setSelectedSensorPosition(0);
    }
}
