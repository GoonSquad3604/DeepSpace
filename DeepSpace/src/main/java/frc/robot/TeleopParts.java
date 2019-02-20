package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import frc.auton.Auton;

public class TeleopParts
{
    private XboxController driveStick;
    private XboxController operateStick;
    private Auton auton;
    public TeleopParts(Auton iAuton, XboxController iDriveStick, XboxController iOperateStick)
    {
        driveStick = iDriveStick;
        operateStick = iOperateStick;
        auton = iAuton;
    }

    public void permitHingeMovement()
    {
        if(operateStick.getYButton())
        {
            if(operateStick.getPOV() == 0)
            {
                auton.getCargoManipulator().runHinge(1);
            }
            else if(operateStick.getPOV() == 180)
            {
                auton.getCargoManipulator().runHinge(-1);
            }
            else
            {
                auton.getCargoManipulator().runHinge(0);
            }
        }
        else
        {
            auton.getCargoManipulator().runHinge(0);
        }
    }
}