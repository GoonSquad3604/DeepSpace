/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.sensors.PigeonIMU;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.ConfigParameter;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Robot extends TimedRobot {
  
    CANSparkMax leftMain;
    CANSparkMax leftSlave;
    CANSparkMax rightMain;
    CANSparkMax rightSlave;

    CANSparkMax frontPillar;
    CANSparkMax rearPillar;

    WPI_TalonSRX pillarDrive;

    WPI_TalonSRX elevatorLeft;
    WPI_TalonSRX elevatorRight;

    WPI_TalonSRX sucker;
    WPI_TalonSRX hinge;
    WPI_TalonSRX hinge2;

    WPI_TalonSRX hatch;

    XboxController driveStick;
    XboxController operatorStick;

    DifferentialDrive driveTrain;

    DigitalInput elevatorLimit;

    double position = 0;
    double initPosition = 0;

    double positionFront = 0;
    double initPositionFront = 0;

    double elevatorPos = 0;

    double elevatorPower = 0;
    double suckerPower = 0;
    double hingePower = 0;
    double hatchPower = 0;

    private double P = 0.02;
    private double I = 0;
    private double D = 0;
    
    private double integral = 0;
    private double derivative = 0;
    private double previous_error = 0; 

    PigeonIMU gyro;
    double[] ypr = new double[3];

    boolean moving = false;
    boolean stopped = false;

    Timer ballTime;

    NetworkTable table;
    @Override
    public void robotInit() {
        
        gyro = new PigeonIMU(0);
        gyro.setYaw(0, 10);

        leftMain = new CANSparkMax(20, MotorType.kBrushless);
        leftSlave = new CANSparkMax(1, MotorType.kBrushless);
        leftSlave.follow(leftMain);
        leftMain.setInverted(true);

        rightMain = new CANSparkMax(15, MotorType.kBrushless);
        rightSlave = new CANSparkMax(14, MotorType.kBrushless);
        rightSlave.follow(rightMain);
        rightMain.setInverted(true);

        //GOOOD CODE
        // rightMain = new CANSparkMax(15, MotorType.kBrushless);
        // rightSlave = new CANSparkMax(14, MotorType.kBrushless);
        // rightSlave.follow(rightMain);
        // rightMain.setInverted(true);
        //END GOOD CODE

      
        // frontPillar = new CANSparkMax(12, MotorType.kBrushless);
        // frontPillar.setInverted(true);
        // rearPillar = new CANSparkMax(13, MotorType.kBrushless);
        // rearPillar.setInverted(false);

        // pillarDrive = new WPI_TalonSRX(2);
        // pillarDrive.setInverted(true);

        elevatorLeft = new WPI_TalonSRX(6);
        elevatorLeft.setInverted(true);
        elevatorRight = new WPI_TalonSRX(5);
        elevatorRight.follow(elevatorLeft);
        

        sucker = new WPI_TalonSRX(3);
        hinge = new WPI_TalonSRX(8);
        hinge.setInverted(true);
        hinge2 = new WPI_TalonSRX(7);

        driveTrain = new DifferentialDrive(leftMain, rightMain);

        driveStick = new XboxController(0);
        operatorStick = new XboxController(1);

        // elevatorLimit = new DigitalInput(0);

        // elevatorLeft.setSelectedSensorPosition(0, 0, 10);

        // hatch = new WPI_TalonSRX(9);

        // initPosition = rearPillar.getEncoder().getPosition();
        // initPositionFront = frontPillar.getEncoder().getPosition();

        // table = NetworkTableInstance.getDefault().getTable("limelight");
        // table.getEntry("camMode").setNumber(1);

        elevatorLeft.config_kP(0, 1, 10);
        elevatorLeft.config_kI(0, 0, 10);
        elevatorLeft.config_kD(0, 0, 10);
        elevatorLeft.configMotionCruiseVelocity(950, 10);
        elevatorLeft.configMotionAcceleration(3000, 10);

        ballTime = new Timer();

    }

    @Override
    public void robotPeriodic() {
        
        //if(elevatorLimit.get()){
        //    elevatorLeft.setSelectedSensorPosition(0, 0, 10);
        //}
    
        elevatorPos = elevatorLeft.getSelectedSensorPosition();

        // position = rearPillar.getEncoder().getPosition();
        // positionFront = frontPillar.getEncoder().getPosition();

        // //System.out.println("Rear: " + (position - initPosition) + " " + "Front: " + (positionFront - initPositionFront));

        System.out.println(elevatorLeft.getSelectedSensorPosition());

        //System.out.println(hinge.getSensorCollection().getAnalogInRaw());

        gyro.getYawPitchRoll(ypr);


    }    

    @Override
    public void autonomousInit() {
    }

    @Override
    public void autonomousPeriodic() {
    }

    @Override
    public void teleopInit() {
        //table.getEntry("camMode").setNumber(1);
        ballTime.start();
        ballTime.reset();

    }

    double axis1 = 0;
    double axis4 = 0;



    @Override
    public void teleopPeriodic() {

      
        axis1 = (Math.abs(driveStick.getRawAxis(1)) > .1) ? 0.9 * driveStick.getRawAxis(1) : 0.0;
        axis4 = (Math.abs(driveStick.getRawAxis(4)) > .1) ? 0.9 * driveStick.getRawAxis(4) : 0.0;
      

        // if(operatorStick.getPOV() == 0){
          
        //   if((position - initPosition) - (positionFront - initPositionFront) > 1){
        //     rearPillar.set(.9);
        //   }
        //   else{
        //     rearPillar.set(1);
        //   }
          
        //   if((positionFront - initPositionFront) - (position - initPosition) > 1){
        //     frontPillar.set(0.9);
        //   }
        //   else{
        //     frontPillar.set(1);
        //   }

         
        //   position = rearPillar.getEncoder().getPosition();
        //   positionFront = frontPillar.getEncoder().getPosition();
        // }
        // else if(operatorStick.getBackButton()){
          
        //     if((position - initPosition) - (positionFront - initPositionFront) > 1){
        //       frontPillar.set(0.4);
        //     }
        //     else{
        //       frontPillar.set(0.0);
        //     }
            
        //     if((position - initPosition) - (positionFront - initPositionFront) > 1){
        //         rearPillar.set(0.4);
        //     }
        //     else{
        //         rearPillar.set(0.0);
        //     }
            
        //     position = rearPillar.getEncoder().getPosition();
        //     positionFront = frontPillar.getEncoder().getPosition();
        //   }
        // else if(operatorStick.getPOV() == 180){
        //   frontPillar.set(-0.5);
        //   rearPillar.set(-0.5);
        // }
        // else if(operatorStick.getAButton()){
        //   frontPillar.set(0);
        //   rearPillar.set(-0.5);
        // }
        // else if(operatorStick.getBButton()){
        //   frontPillar.set(0);
        //   rearPillar.set(0.5);
        // }
        // else if(operatorStick.getXButton()){
        //   frontPillar.set(-0.5);
        //   rearPillar.set(0);
        // }
        // else if(operatorStick.getYButton()){
        //   frontPillar.set(0.5);
        //   rearPillar.set(0);
        // }
        // else{
        //   frontPillar.set(0);
        //   rearPillar.set(0);
        // }

        
      
        if(operatorStick.getBumper(Hand.kLeft)){
            suckerPower = 1;
        }
        else if(operatorStick.getBumper(Hand.kRight)){
            suckerPower = -1;
        }
        else if(moving){
            if(Math.abs(elevatorLeft.getSelectedSensorVelocity()) < 100 && operatorStick.getAButton()){
                if(ballTime.get() < 0.25){
                    suckerPower = 1;
                }
                else{
                    moving = false;
                }
            }
            else if(!operatorStick.getAButton()){
                moving = false;
            }
            else{
                ballTime.reset();
                suckerPower = 0;
            }
        }
        else{
            suckerPower = 0;
        }


        // if(operatorStick.getPOV() == 0){
        //     elevatorPower =  operatorStick.getAButton() ? 0.7 : 0;
        //     hingePower = (operatorStick.getXButton() && hinge.getSensorCollection().getAnalogInRaw() < 830) ?  1 : 0;
        // }
        // else if(operatorStick.getPOV() == 90){
        //     hatchPower = operatorStick.getBButton() ? 0.3 : 0;
        // }
        // else if(operatorStick.getPOV() == 180){
        //     elevatorPower =  (operatorStick.getAButton() && elevatorPos > 1600) ? -0.65 : 0;
        //     hingePower = operatorStick.getXButton() ?  -1 : 0;
        // }
        // else if(operatorStick.getPOV() == 270){
        //     hatchPower = operatorStick.getBButton() ? -0.3 : 0;
        // }
        // else{
        //     elevatorPower = 0;
        //     hingePower = 0;
        //     hatchPower = 0;
        // }
        
        if(operatorStick.getPOV() == 0){
            if(Math.abs(elevatorLeft.getSelectedSensorVelocity()) > 100){
                moving = true;
            }
            elevatorLeft.set(ControlMode.MotionMagic, 27000);
        }
        else if(operatorStick.getPOV() == 90){
            if(Math.abs(elevatorLeft.getSelectedSensorVelocity()) > 100){
                moving = true;
            }
            elevatorLeft.set(ControlMode.MotionMagic, 14000);
        }
        else if(operatorStick.getPOV() == 180){
            if(Math.abs(elevatorLeft.getSelectedSensorVelocity()) > 100){
                moving = true;
            }
            elevatorLeft.set(ControlMode.MotionMagic, 2000);
        }
        else if(operatorStick.getPOV() == 270){
            if(Math.abs(elevatorLeft.getSelectedSensorVelocity()) > 100){
                moving = true;
            }
            elevatorLeft.set(ControlMode.MotionMagic, 8500);
        }
        else if(operatorStick.getBButton()){
            elevatorLeft.set(-0.5);
        }
        else if(operatorStick.getXButton()){
            elevatorLeft.set(0.5);
        }
        else{
            elevatorLeft.set(0);
        }
       

        
        //elevatorRight.set(elevatorPower);
        hinge.set(hingePower);
        hinge2.set(hingePower);
        sucker.set(suckerPower);
      
        // pillarDrive.set(operatorStick.getTriggerAxis(Hand.kRight));
            
        

        driveTrain.arcadeDrive(axis1, -axis4);
      
        //System.out.println(hinge.getSensorCollection().getAnalogInRaw());
        //System.out.println(elevatorPos);
        // System.out.println("Rear: " + (position - initPosition) + " " + "Front: " + (positionFront - initPositionFront));

        // if(operatorStick.getPOV() == 0){
        //     if(PID(10) < 0.3 && PID(10) > 0.05)
        //     {
        //        driveTrain.arcadeDrive(0, 0.3); 
        //     }
        //     else{
        //         driveTrain.arcadeDrive(0, PID(10));
        //     }
            
        // }
        // else if(operatorStick.getPOV() == 180){
        //     gyro.setYaw(0, 10);
        // }
        // System.out.println(ypr[0]);
        // SmartDashboard.putNumber("Target", 50);
        // SmartDashboard.putNumber("Angle", ypr[0]);
        



    }





    @Override
    public void testInit() 
    {
      
    }

    @Override
    public void testPeriodic() 
    {

    }

    public double PID(double targetAngle)
    { 
        double error = targetAngle - ypr[0]; // Error = Target - Actual
        integral += (error*.02); // Integral is increased by the error*time (which is .02 seconds using normal IterativeRobot)
        derivative = (error - previous_error) / .02;
        return P*error + I*integral + D*derivative;
    }

}
