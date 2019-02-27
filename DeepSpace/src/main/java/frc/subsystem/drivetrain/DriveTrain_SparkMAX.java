package frc.subsystem.drivetrain;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;
import static frc.robot.Constants.*;

public class DriveTrain_SparkMAX extends DriveTrain
{

    private CANSparkMax leftFront, leftRear, rightFront, rightRear;
    
    /**************/
    /*Constructors*/
    /**************/

    private double neutralLeft = 0;
    private double neutralRight = 0;
    /**
     *sets slaves to follow mains, sets some motors inveted false
     * @param leftFront sets leftFront left front motor
     * @param leftRear sets leftRear for left rear motor
     * @param rightFront sets rightFront for right front motor
     * @param rightRear Sets rightRear for right rear motor
     */
    private DriveTrain_SparkMAX(CANSparkMax leftFront, CANSparkMax leftRear, CANSparkMax rightFront, CANSparkMax rightRear)
    {

        super(leftFront, rightFront);
        
        this.leftFront = leftFront;
        this.leftRear = leftRear;
        this.rightFront = rightFront;
        this.rightRear = rightRear;

        this.leftRear.follow(this.leftFront);
        this.leftFront.setInverted(false);
        this.rightRear.follow(this.rightFront);
        this.rightFront.setInverted(false);            
    }
    /**
     * sets IDs, and sets motor type brushless 
     * @param leftFrontID sets ID for leftFront
     * @param leftRearID sets ID for leftRear 
     * @param rightFrontID sets ID for rightFront
     * @param rightRearID sets ID for rightRear
     */
    public DriveTrain_SparkMAX(int leftFrontID, int leftRearID, int rightFrontID, int rightRearID)
    {
        
        this(new CANSparkMax(leftFrontID, com.revrobotics.CANSparkMaxLowLevel.MotorType.kBrushless), 
        new CANSparkMax(leftRearID, com.revrobotics.CANSparkMaxLowLevel.MotorType.kBrushless), 
        new CANSparkMax(rightFrontID, com.revrobotics.CANSparkMaxLowLevel.MotorType.kBrushless), 
        new CANSparkMax(rightRearID, com.revrobotics.CANSparkMaxLowLevel.MotorType.kBrushless));

    }


    /*********/
    /*Methods*/
    /*********/

    /**
     * sets left to a value
     * @param value 
     */
    @Override
    public void setLeft(double value) 
    {
        
    }
    /**
     *  sets right to a value
     * @param value
     */
    @Override
    public void setRight(double value) 
    {

	}
    /**
     * sets the encoder on left to position
     * @param position position of the encoder  
     */
    @Override
    public void setLeftPosition(double position) 
    {
        neutralLeft = (leftFront.getEncoder().getPosition()) + position;
    }
    /**
     * sets the encoder on the right to position 
     * @param position position of encoder
     */
    @Override
    public void setRightPosition(double position) 
    {
        neutralRight = (rightFront.getEncoder().getPosition()) + position;
    }

    /**
     * returns the position of the left encoder if it is neutral or not
     * @return position if neutral 
     */
    @Override
    public double getLeftPosition() 
    {
        return (leftFront.getEncoder().getPosition()) - neutralLeft;
    }

    /**
     * returns the position of the right encoder if it is neutral or not
     * @return
     */
    @Override
    public double getRightPosition() 
    {
        return (leftFront.getEncoder().getPosition()) - neutralRight;
    }

    /**
     * gets the inched for left
     * @return Inches for left
     */
    @Deprecated
    public double getLeftInches() 
    {
        return getLeftPosition();
    }

    /**
     * gets inches for right
     * @return Inches for right
     */
    @Deprecated
    public double getRightInches() 
    {
        return getRightPosition();
    }

    /**
     * resets the drive train
     * @return drive train reset 
     */
    @Override
    public void resetDriveTrain() 
    {

    }

    
}