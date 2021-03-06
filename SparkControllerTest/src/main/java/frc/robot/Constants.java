package frc.robot;

/***********/
/*CONSTANTS*/
/***********/

public class Constants
{

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
    public static final int kOpenCloseID = 0;

    /*******************/
    /*Elevator Settings*/
    /*******************/
    
    //PID Elevator
    public static final double kElevatorP = 0.0;
    public static final double kElevatorI = 0.0;
    public static final double kElevatorD = 0.0;
    public static final double kElevatorF = 0.0;
    public static final int kElevatorCargoVel = 0;
    public static final int kElevatorCargoAcc = 0;

    public static final double kopenCloseP = 0.0;
    public static final double kopenCloseI = 0.0;
    public static final double kopenCloseD = 0.0;
    public static final double kopenCloseF = 0.0;

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

    public static final double kRobotWidth = 0.0; //Meters
    public static final double kWheelDiameter = 6.0; //Inches
    public static final double kGearRatio = 10.12; //Number of motor revs per wheel rev
    public static final double kPulsePerMotorRevolution = 42.0;

    //DO NOT CHANGE
    public static final double kWheelCircumference = kWheelDiameter * Math.PI;
    public static final double kPulsePerInch = (kGearRatio * kPulsePerMotorRevolution) / kWheelCircumference;

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

    /*******/
    /*Auton*/
    /*******/

    public static final double kTurnError = 1.0; //Acceptable error (degrees) before ending turn

    /**********/
    /*Commands*/
    /**********/

    public static final double kElevatorError = 0;
    public static final int kOpenBeak = 0;
    public static final int kClosedBeak = 0;
    public static final int kReflectiveTapePipeline = 0;

    /*********/
    /*Pillars*/
    /*********/
    public static final double kPillarWheelDistance = 0;
    public static final double kPillarHeight = 0;
    
}