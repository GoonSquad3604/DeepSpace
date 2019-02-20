package frc.subsystem;

import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.XboxController;

import static frc.robot.Constants.*;
import frc.subsystem.drivetrain.DriveTrain;
import frc.subsystem.drivetrain.DriveTrain_TalonSRX;

import static frc.robot.Constants.*;

public class Pillars {

    private WPI_TalonSRX  wheels;
    private CANSparkMax frontSide, rearSide;
    private double frontInitPos = 0;
    private double rearInitPos = 0;
    private double frontPos;
    private double rearPos;

    public Pillars(int frontSideID, int rearSideID, int wheelsID) {
        frontSide = new CANSparkMax(frontSideID,com.revrobotics.CANSparkMaxLowLevel.MotorType.kBrushless);
        frontSide.setInverted(true);
        rearSide = new CANSparkMax(rearSideID,com.revrobotics.CANSparkMaxLowLevel.MotorType.kBrushless);
        rearSide.setInverted(true);
        wheels = new WPI_TalonSRX(wheelsID);
        wheels.setInverted(false);
        frontInitPos = frontSide.getEncoder().getPosition();
        rearInitPos = frontSide.getEncoder().getPosition();


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
        return (getFrontHeight() + getRearHeight()) / 2.0;
    }
    public double getDistance()
    {
        return 0;//wheels.getSelectedSensorPosition() * kPulsesPerInchPillarWheels;
    }

    //@return the distance the pillars have travelled.

    //@return the height of the pillars 
    public double getFrontHeight()
    {
        return frontInitPos - (frontSide.getEncoder().getPosition()*kInchPerRotationPillar);
    }
    public double getRearHeight()
    {
        return rearInitPos - (rearSide.getEncoder().getPosition()*kInchPerRotationPillar);
    }

    //Resets the pillar subsystem.
    public void reset()
    { 
        
        frontInitPos = frontSide.getEncoder().getPosition()*kInchPerRotationPillar;
        rearInitPos = rearSide.getEncoder().getPosition()*kInchPerRotationPillar;

    }
    public void runOldChadCode(XboxController driveStick)
    {

        if(driveStick.getPOV() == 0){
          
          if((rearPos - rearInitPos) - (frontPos - frontInitPos) > 1){
            rearSide.set(.9);
          }
          else{
            rearSide.set(1);
          }
          
          if((frontPos - frontInitPos) - (rearPos - rearInitPos) > 1){
            frontSide.set(0.9);
          }
          else{
            frontSide.set(1);
          }

         
          rearPos = rearSide.getEncoder().getPosition();
          frontPos = frontSide.getEncoder().getPosition();
        }
        else if(driveStick.getBackButton()){
          
            if((rearPos - rearInitPos) - (frontPos - frontInitPos) > 1){
              frontSide.set(0.4);
            }
            else{
              frontSide.set(0.0);
            }
            
            if((rearPos - rearInitPos) - (frontPos - frontInitPos) > 1){
                rearSide.set(0.4);
            }
            else{
                rearSide.set(0.0);
            }
            
            rearPos = rearSide.getEncoder().getPosition();
            frontPos = frontSide.getEncoder().getPosition();
          }
        else if(driveStick.getPOV() == 180){
          frontSide.set(-0.5);
          rearSide.set(-0.5);
        }
        else if(driveStick.getAButton()){
          frontSide.set(0);
          rearSide.set(-0.5);
        }
        else if(driveStick.getBButton()){
          frontSide.set(0);
          rearSide.set(0.5);
        }
        else if(driveStick.getXButton()){
          frontSide.set(-0.5);
          rearSide.set(0);
        }
        else if(driveStick.getYButton()){
          frontSide.set(0.5);
          rearSide.set(0);
        }
        else{
          frontSide.set(0);
          rearSide.set(0);
        }
    }

}