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
        neutralLeft = (leftFront.getEncoder().getPosition()) + position;
    }

    @Override
    public void setRightPosition(double position) 
    {
        neutralRight = (rightFront.getEncoder().getPosition()) + position;
    }

    @Override
    public double getLeftPosition() 
    {
        return (leftFront.getEncoder().getPosition()) - neutralLeft;
    }

    @Override
    public double getRightPosition() 
    {
        return (leftFront.getEncoder().getPosition()) - neutralRight;
    }

    public double getLeftInches() 
    {
        return getLeftPosition() * kInchesPerMotorRev;
    }

    public double getRightInches() 
    {
        return getRightPosition() * kInchesPerMotorRev;
    }

    @Override
    public void resetDriveTrain() 
    {

    }

    @Override
    public WPI_TalonSRX gyroTest() 
    {
        return null;
    }

}