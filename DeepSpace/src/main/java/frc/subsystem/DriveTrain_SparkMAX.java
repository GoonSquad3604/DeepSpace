package frc.subsystem;

import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Constants;

public class DriveTrain_SparkMAX extends DifferentialDrive{

    private CANSparkMax leftFront, leftRear, rightFront, rightRear;

    private DriveTrain_SparkMAX(CANSparkMax leftFront, CANSparkMax leftRear, 
    CANSparkMax rightFront, CANSparkMax rightRear)
    {

        super(leftFront, rightFront);
        
        this.leftFront = leftFront;
        this.leftRear = leftRear;
        this.rightFront = rightFront;
        this.rightRear = rightRear;

        this.leftRear.follow(leftFront);
        this.rightRear.follow(rightFront);
            
    }

    public DriveTrain_SparkMAX(int leftFrontID, int leftRearID, int rightFrontID, int rightRearID)
    {
        this(new CANSparkMax(leftFrontID, com.revrobotics.CANSparkMaxLowLevel.MotorType.kBrushless), 
        new CANSparkMax(leftRearID, com.revrobotics.CANSparkMaxLowLevel.MotorType.kBrushless), 
        new CANSparkMax(rightFrontID, com.revrobotics.CANSparkMaxLowLevel.MotorType.kBrushless), 
        new CANSparkMax(rightRearID, com.revrobotics.CANSparkMaxLowLevel.MotorType.kBrushless));

    }

    public double getLeftPosition()
    {
        return leftFront.getEncoder().getPosition();
    }

    public double getRightPosition()
    {
        return rightFront.getEncoder().getPosition();
    }

    public void resetDriveTrain()
    {
        // TODO
    }
    
    public CANSparkMax getLeftMotor()
    {
        return leftFront;
    }
    
    public CANSparkMax getRightMotor()
    {
        return rightFront;
    }
    
    public CANSparkMax getLeftSlave()
    {
        return leftRear;
    }
    
    public CANSparkMax getRightSlave()
    {
        return rightRear;
    }

}