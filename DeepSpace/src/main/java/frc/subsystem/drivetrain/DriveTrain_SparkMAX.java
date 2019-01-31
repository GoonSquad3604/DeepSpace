package frc.subsystem.drivetrain;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Constants;

public class DriveTrain_SparkMAX extends DifferentialDrive{

    private WPI_TalonSRX leftFront, leftRear, rightFront, rightRear;
    
    private DriveTrain_SparkMAX(WPI_TalonSRX leftFront, WPI_TalonSRX leftRear, WPI_TalonSRX rightFront, WPI_TalonSRX rightRear){

        super(leftFront, rightFront);
        
        this.leftFront = leftFront;
        this.leftRear = leftRear;
        this.rightFront = rightFront;
        this.rightRear = rightRear;

        this.leftRear.follow(leftFront);
        this.rightRear.follow(rightFront);
            
    }

    public DriveTrain_SparkMAX(int leftFrontID, int leftRearID, int rightFrontID, int rightRearID){
        
        this(new WPI_TalonSRX(leftFrontID), new WPI_TalonSRX(leftRearID), new WPI_TalonSRX(rightFrontID), new WPI_TalonSRX(rightRearID));

    }

    public int getLeftPosition(){
        return leftFront.getSelectedSensorPosition(0);
    }

    public int getRightPosition(){
        return rightFront.getSelectedSensorPosition(0);
    }

    public void resetDriveTrain(){
        leftFront.configFactoryDefault(Constants.kTimeoutMs);
        leftRear.configFactoryDefault(Constants.kTimeoutMs);
        rightFront.configFactoryDefault(Constants.kTimeoutMs);
        rightRear.configFactoryDefault(Constants.kTimeoutMs);
    }
    public WPI_TalonSRX getLeftMotor()
    {
        return leftFront;
    }
    public WPI_TalonSRX getRightMotor()
    {
        return rightFront;
    }
    public WPI_TalonSRX getLeftSlave()
    {
        return leftRear;
    }
    public WPI_TalonSRX getRightSlave()
    {
        return rightRear;
    }

}