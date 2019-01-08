package frc.subsystem;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class DriveTrain extends DifferentialDrive{

    private WPI_TalonSRX frontRight, frontLeft, rearRight, rearLeft;
    
    private DriveTrain(WPI_TalonSRX frontLeft, WPI_TalonSRX frontRight, WPI_TalonSRX rearLeft, WPI_TalonSRX rearRight){

        super(new SpeedControllerGroup(frontLeft, rearLeft), new SpeedControllerGroup(frontRight, rearRight));
        
        this.frontLeft = frontLeft;
        this.frontRight = frontRight;
        this.rearLeft = rearLeft;
        this.rearRight = rearRight;
            
    }

    public DriveTrain(int frontLeft, int frontRight, int rearLeft, int rearRight){
        
        this(new WPI_TalonSRX(frontLeft), new WPI_TalonSRX(frontRight), 
        new WPI_TalonSRX(frontLeft), new WPI_TalonSRX(frontRight));

    }

}