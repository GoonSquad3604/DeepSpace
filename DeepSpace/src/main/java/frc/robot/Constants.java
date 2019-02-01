package frc.robot;

/***********/
/*CONSTANTS*/
/***********/

public class Constants{

    /*********/
    /*General*/
    /*********/

    public static int kTimeoutMs = 10;

    /***********/
    /*Talon IDs*/
    /***********/

    public static final int kLeftFrontID = 0;
    public static final int kLeftRearID = 0;
    public static final int kRightFrontID = 0;
    public static final int kRightRearID = 0;

    public static final int kElevatorID = 0;

    /*******************/
    /*Elevator Settings*/
    /*******************/
    
    //PID Cargo
    public static final double kElevatorCargoP = 0.0;
    public static final double kElevatorCargoI = 0.0;
    public static final double kElevatorCargoD = 0.0;
    public static final double kElevatorCargoF = 0.0;
    public static final int kElevatorCargoVel = 0;
    public static final int kElevatorCargoAcc = 0;

    //PID Hatch
    public static final double kElevatorHatchP = 0.0;
    public static final double kElevatorHatchI = 0.0;
    public static final double kElevatorHatchD = 0.0;
    public static final double kElevatorHatchF = 0.0;
    public static final int kElevatorHatchVel = 0;
    public static final int kElevatorHatchAcc = 0;

    //Rocket hatch encoder positions
    public static final double kBottomRocketHatch = 0;
    public static final double kMiddleRocketHatch = 0;
    public static final double kTopRocketHatch = 0;

    //Rocket cargo encoder positions
    public static final double kBottomRocketCargo = 0;
    public static final double kMiddleRocketCargo = 0;
    public static final double kTopRocketCargo = 0;

    /*************/
    /*Drive Train*/
    /*************/

    public static final double kPulsePerInch = 51.0;

    public static final double kDt = 0;
    public static final double kVelocity = 0;
    public static final double kAcceleration = 0;
    public static final double kJerk = 0;

    //Turn PID
    public static final double kTurnP = 0.08;
    public static final double kTurnI = 0.0;
    public static final double kTurnD = 0.0;

    //Drive PID
    public static final double kDriveP = 0;
    public static final double kDriveI = 0;
    public static final double kDriveD = 0;
    public static final double kVelocityRatio = 0;
    public static final double kAccelerationRatio = 0;

    public static final double kRobotWidth = 0;
    public static final double kWheelDiameter = 0;

    /*******/
    /*Auton*/
    /*******/

    public static final double kTurnError = 1.0; //Acceptable error (degrees) before ending turn

    //A highly specific number that is used to tell the drivetrain to NOT move whatever it is in. Useful when merging commands.
    public static final double kIgnoreDrive = 0.0128455731355933733083634403378339365501238233033000037443673447390304846743934939476;  
}