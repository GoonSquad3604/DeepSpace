package frc.subsystem;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class DriveTrain extends DifferentialDrive{

    private WPI_TalonSRX leftFront, leftRear, rightFront, rightRear;
    
    private DriveTrain(WPI_TalonSRX leftFront, WPI_TalonSRX leftRear, WPI_TalonSRX rightFront, WPI_TalonSRX rightRear){

        super(new SpeedControllerGroup(leftFront, leftRear), new SpeedControllerGroup(rightFront, rightRear));
        
        this.leftFront = leftFront;
        this.leftRear = leftRear;
        this.rightFront = rightFront;
        this.rightRear = rightRear;
            
    }

    public DriveTrain(int leftFrontID, int leftRearID, int rightFrontID, int rightRearID){
        
        this(new WPI_TalonSRX(leftFrontID), new WPI_TalonSRX(leftRearID), 
        new WPI_TalonSRX(rightFrontID), new WPI_TalonSRX(rightRearID));

    }

}