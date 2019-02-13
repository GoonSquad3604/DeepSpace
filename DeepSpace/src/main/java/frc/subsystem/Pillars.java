package frc.subsystem;

import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;

import static frc.robot.Constants.*;
import frc.subsystem.drivetrain.DriveTrain;
import frc.subsystem.drivetrain.DriveTrain_TalonSRX;

import static frc.robot.Constants.*;

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
    public void setFrontPillar(double speed)
    {
        frontSide.set(speed);
    }
    public void setRearPillar(double speed)
    {
        rearSide.set(speed);
    }
    public double getHeight()
    {
        return (Math.abs(frontSide.getEncoder().getPosition()*kInchPerRotationPillar) + Math.abs(rearSide.getEncoder().getPosition()*kInchPerRotationPillar)) / 2;
    }
    public double getDistance()
    {
        return 0;//wheels.getSelectedSensorPosition() * kPulsesPerInchPillarWheels;
    }

    //@return the distance the pillars have travelled.

    //@return the height of the pillars 
    public double getFrontHeight()
    {
        return frontSide.getEncoder().getPosition()*kInchPerRotationPillar;
    }
    public double getRearHeight()
    {
        return rearSide.getEncoder().getPosition()*kInchPerRotationPillar;
    }

    //Resets the pillar subsystem.
    public void reset()
    { //Fun
     //rearSide.Encoder.reset();
     //frontSide.Encoder.reset();
    }

}