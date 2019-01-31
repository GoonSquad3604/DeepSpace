package frc.subsystem.drivetrain;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;

import frc.robot.Constants;

public class DriveTrain_SparkMAX extends DriveTrain{

    private CANSparkMax leftFront, leftRear, rightFront, rightRear;
    
    private DriveTrain_SparkMAX(CANSparkMax leftFront, CANSparkMax leftRear, CANSparkMax rightFront, CANSparkMax rightRear){

        super(leftFront, rightFront);
        
        this.leftFront = leftFront;
        this.leftRear = leftRear;
        this.rightFront = rightFront;
        this.rightRear = rightRear;

        this.leftRear.follow(this.leftFront);
        this.rightRear.follow(this.rightFront);
            
    }

    public DriveTrain_SparkMAX(int leftFrontID, int leftRearID, int rightFrontID, int rightRearID){
        
        this(new CANSparkMax(leftFrontID, com.revrobotics.CANSparkMaxLowLevel.MotorType.kBrushless), 
        new CANSparkMax(leftRearID, com.revrobotics.CANSparkMaxLowLevel.MotorType.kBrushless), 
        new CANSparkMax(rightFrontID, com.revrobotics.CANSparkMaxLowLevel.MotorType.kBrushless), 
        new CANSparkMax(rightRearID, com.revrobotics.CANSparkMaxLowLevel.MotorType.kBrushless));

    }

    @Override
    public int getLeftPosition() {
        return 0;
    }

    @Override
    public int getRightPosition() {
        return 0;
    }

    @Override
    public void resetDriveTrain() {

    }

}