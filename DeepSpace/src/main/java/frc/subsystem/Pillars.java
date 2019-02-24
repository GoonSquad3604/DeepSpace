package frc.subsystem;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import static frc.robot.Constants.*;

public class Pillars {

    private WPI_TalonSRX  wheels;
    private CANSparkMax frontSide, rearSide;
    private double frontInitPos = 0;
    private double rearInitPos = 0;
    private double frontPos;
    private double rearPos;

    /**
     * Pillars to lift the robot up
     * @param frontSideID Motor ID of front pillar
     * @param rearSideID Motor ID of rear pillar
     * @param wheelsID Motor ID of pillar wheels
     */
    public Pillars(int frontSideID, int rearSideID, int wheelsID) {
        
        frontSide = new CANSparkMax(frontSideID,com.revrobotics.CANSparkMaxLowLevel.MotorType.kBrushless);
        frontSide.setInverted(false);
        rearSide = new CANSparkMax(rearSideID,com.revrobotics.CANSparkMaxLowLevel.MotorType.kBrushless);
        rearSide.setInverted(false);
        wheels = new WPI_TalonSRX(wheelsID);
        wheels.setInverted(true);
        frontInitPos = frontSide.getEncoder().getPosition();
        rearInitPos = frontSide.getEncoder().getPosition();

    }

    /**
     * Drive pillar wheels
     * @param speed Speed from -1 to 1
     */
    public void moveWheels(double speed) 
    {
        wheels.set(speed);    
    }
    
    /**
     * Run both pillars
     * @param speed Speed from -1 to 1
     */
    public void setPillars(double speed)
    {
      frontSide.set(speed);
      rearSide.set(speed);  
    }

    /**
     * Run front pillar
     * @param speed Speed from -1 to 1
     */
    public void setFrontPillar(double speed)
    {
        frontSide.set(speed);
    }

    /**
     * Run rear pillar
     * @param speed Speed from -1 to 1
     */
    public void setRearPillar(double speed)
    {
        rearSide.set(speed);
    }

    /**
     * The height of the robot
     * @return Average amount of pillar motor rotations
     */
    public double getHeight()
    {
        return (getFrontHeight() + getRearHeight()) / 2.0;
    }

    public double getDistance()
    {
        return 0;//wheels.getSelectedSensorPosition() * kPulsesPerInchPillarWheels;
    }

    /**
     * The height of the front of the robot
     * @return Amount of the front pillar motor rotations
     */
    public double getFrontHeight()
    {
        return (frontSide.getEncoder().getPosition()) - frontInitPos;
    }

    /**
     * The height of the rear of the robot
     * @return Amount of the rear pillar motor rotations
     */
    public double getRearHeight()
    {
        return (rearSide.getEncoder().getPosition()) - rearInitPos;
    }

    //Resets the pillar encoders
    public void resetPosition()
    { 
        frontInitPos = frontSide.getEncoder().getPosition();
        rearInitPos = rearSide.getEncoder().getPosition();
    }

    public void resetFrontPosition()
    { 
        frontInitPos = frontSide.getEncoder().getPosition();
    }

    public void resetRearPosition()
    { 
        rearInitPos = rearSide.getEncoder().getPosition();
    }

    /**Runs the pillars under the driver's control
     * @param driveStick Driver XboxController
     */
    public void runManualPillars(XboxController driveStick)
    {
      if(driveStick == null)
      {
        return;
      }
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

        if(driveStick.getTriggerAxis(Hand.kLeft)>0.2)
        {
          wheels.set(driveStick.getTriggerAxis(Hand.kLeft));
        }
        else if(driveStick.getTriggerAxis(Hand.kRight)>0.2)
        {
          wheels.set(-driveStick.getTriggerAxis(Hand.kRight));
        }
        else
        {
          wheels.set(0);
        }
    }

}