package frc.subsystem.drivetrain;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import static frc.robot.Constants.*;

public class DriveTrain_TalonSRX extends DriveTrain
{

    private WPI_TalonSRX leftFront, leftRear, rightFront, rightRear;
    
    /***************/
    /*Constructors*/
    /**************/

    private DriveTrain_TalonSRX(WPI_TalonSRX iLeftFront, WPI_TalonSRX iLeftRear, WPI_TalonSRX iRightFront, WPI_TalonSRX iRightRear)
    {

        super(iLeftFront, iRightFront);
        
        leftFront = iLeftFront;
        leftRear = iLeftRear;
        rightFront = iRightFront;
        rightRear = iRightRear;

        leftRear.follow(leftFront);
        rightRear.follow(rightFront);
            
    }

    public DriveTrain_TalonSRX(int leftFrontID, int leftRearID, int rightFrontID, int rightRearID)
    {
        
        this(new WPI_TalonSRX(leftFrontID), new WPI_TalonSRX(leftRearID), new WPI_TalonSRX(rightFrontID), new WPI_TalonSRX(rightRearID));

    }


    /*********/
    /*Methods*/
    /*********/


    @Override
    public void setLeft(double value) 
    {

    }

    @Override
    public void setRight(double value) 
    {

	}

    @Override
    public void setLeftPosition(double position) 
    {
        leftFront.setSelectedSensorPosition((int)position);
    }

    @Override
    public void setRightPosition(double position) 
    {
        rightFront.setSelectedSensorPosition((int)position);
    }

    @Override
    public double getLeftPosition() 
    {
        return leftFront.getSelectedSensorPosition(0);
    }

    @Override
    public double getRightPosition() 
    {
        return rightFront.getSelectedSensorPosition(0);
    }

    @Override
    public double getLeftInches() {
        return 0;
    }

    @Override
    public double getRightInches() {
        return 0;
    }

    @Override
    public void resetDriveTrain() 
    {
        leftFront.configFactoryDefault(kTimeoutMs);
        leftRear.configFactoryDefault(kTimeoutMs);
        rightFront.configFactoryDefault(kTimeoutMs);
        rightRear.configFactoryDefault(kTimeoutMs);
    }

    @Override
    public WPI_TalonSRX gyroTest() 
    {
        return rightRear;
    }

    
}