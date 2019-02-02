package frc.subsystem;

import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;

import frc.robot.Constants;
import frc.subsystem.drivetrain.DriveTrain;
import frc.subsystem.drivetrain.DriveTrain_TalonSRX;

public class Pillars {

    private WPI_TalonSRX  wheels;
    private CANSparkMax frontSide, rearSide;
    public Pillars(int frontSideID, int rearSideID, int wheelsID) {
        frontSide = new CANSparkMax(frontSideID,com.revrobotics.CANSparkMaxLowLevel.MotorType.kBrushless);
        frontSide.setInverted(true);
        rearSide = new CANSparkMax(rearSideID,com.revrobotics.CANSparkMaxLowLevel.MotorType.kBrushless);
        rearSide.setInverted(true);
        wheels = new WPI_TalonSRX(wheelsID);
        wheels.setInverted(false);
    }

    // Moves the wheels to a specific speed.
    public void moveWheels(double speed) 
    {
     wheels.set(speed);    
    }
    
    //Sets the pillars themselves to a specific speed.
    public void setPillars(double speed)
    {
      frontSide.set(speed);
      rearSide.set(speed);  
    }

    //@return the distance the pillars have travelled.
    public double getDistance()
    { 
      frontSide.getEncoder().getPosition();
      rearSide.getEncoder().getPosition();
        return 0;
    }

    //@return the height of the pillars 
    public double getFrontHeight()
    {
        return frontSide.getEncoder().getPosition();
    }
    public double getRearHeight()
    {
        return rearSide.getEncoder().getPosition();
    }

    //Resets the pillar subsystem.
    public void reset()
    { //Fun
     rearSide.getEncoder().reset;  
     frontSide.getEncoder().reset;
    }

}