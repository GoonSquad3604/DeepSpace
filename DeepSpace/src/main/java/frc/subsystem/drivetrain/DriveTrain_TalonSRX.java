package frc.subsystem.drivetrain;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import static frc.robot.Constants.*;

public class DriveTrain_TalonSRX extends DriveTrain
{

    private WPI_TalonSRX leftFront, leftRear, rightFront, rightRear;
    
    /***************/
    /*Constructors*/
    /**************/

    /**
     * sets the motors to i(motor name)
     * @param iLeftFront sets LeftFront to iLeftFront
     * @param iLeftRear sets LeftRear to iLeftRear
     * @param iRightFront sets RightFront to iRightFront
     * @param iRightRear sets RightRear to iRightRear
     */
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
/**
     * sets IDs, and sets new WPI_TalonSRX 
     * @param leftFrontID sets ID for leftFront
     * @param leftRearID sets ID for leftRear 
     * @param rightFrontID sets ID for rightFront
     * @param rightRearID sets ID for rightRear
     */
    public DriveTrain_TalonSRX(int leftFrontID, int leftRearID, int rightFrontID, int rightRearID)
    {
        
        this(new WPI_TalonSRX(leftFrontID), new WPI_TalonSRX(leftRearID), new WPI_TalonSRX(rightFrontID), new WPI_TalonSRX(rightRearID));

    }


    /*********/
    /*Methods*/
    /*********/

    /**
     * sets left to a value
     * @param value lefts set value
     */
    @Override
    public void setLeft(double value) 
    {

    }

    /**
     * right set to a value
     * @param value rights set value
     */
    @Override
    public void setRight(double value) 
    {

	}

    /**
     * selects sensor position left
     * @param position selected left sensor position
     */
    @Override
    public void setLeftPosition(double position) 
    {
        leftFront.setSelectedSensorPosition((int)position);
    }

    /**
     * selects sensor position right
     * @param position selected right sensor position
     */
    @Override
    public void setRightPosition(double position) 
    {
        rightFront.setSelectedSensorPosition((int)position);
    }

    /**
     * gets the sensor selected left position 0
     * @return selected position left 0
     */
    @Override
    public double getLeftPosition() 
    {
        return leftFront.getSelectedSensorPosition(0);
    }

    /**
     * gets the sensor selected right position 0
     * @return selected position right 0
     */
    @Override
    public double getRightPosition() 
    {
        return rightFront.getSelectedSensorPosition(0);
    }

    /**
     *  gets inches for left
     * @return inches left
     */
    @Override
    public double getLeftInches() {
        return 0;
    }

    /**
     * gets inche for right
     * @return inches right
     */
    @Override
    public double getRightInches() {
        return 0;
    }

    /**
     * resets the drive train 
     * @return drive train rested?
     */
    @Override
    public void resetDriveTrain() 
    {
        leftFront.configFactoryDefault(kTimeoutMs);
        leftRear.configFactoryDefault(kTimeoutMs);
        rightFront.configFactoryDefault(kTimeoutMs);
        rightRear.configFactoryDefault(kTimeoutMs);
    }

    
}