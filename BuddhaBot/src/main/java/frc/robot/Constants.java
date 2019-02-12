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
    public static final int kLeftRearID = 1;
    public static final int kRightFrontID = 3;
    public static final int kRightRearID = 2;

    public static final int kElevatorID = 5;
    public static final int kElevatorSlaveID = 6;
    public static final int kOpenCloseID = 0;

    public static final int kIntakeControlID = 0;
    public static final int kHingeRightID = 0;
    public static final int kHingeLeftID = 0;
    
    public static final int kHatchLeftRightID = 0;
    public static final int kHatchForwardBackID = 0;

    public static final int kPillarsLeftID = 0;
    public static final int kPillarsRightID = 0;
    public static final int kPillarWheelsID = 0;

    public static final double kPulsesPerInchTalon = 0;
    public static final double kPulsesPerInchPillarWheels = 0;
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

    //DO NOT CHANGE
    public static final double kWheelCircumference = kWheelDiameter * Math.PI;
    public static final double kInchesPerMotorRev = kWheelCircumference / kGearRatio;

    public static final double kDt = 0;
    public static final double kVelocity = 0;
    public static final double kAcceleration = 0;
    public static final double kJerk = 0;

    //Turn PID
    public static final double kTurnP = 0.07;
    public static final double kTurnI = 0.1;
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
    public static final double kDistanceError = 1;
    /**********/
    /*Commands*/
    /**********/

    public static final double kElevatorError = 0;
    public static final int kOpenLotus = 0;
    public static final int kClosedLotus = 0;
    public static final int kReflectiveTapePipeline = 0;
    public static final int KForwardLotus = 0;
    public static final int KBackwardLotus = 0;

    /*********/
    /*Pillars*/
    /*********/
    
    public static final double kPillarWheelDistance = 0;
    public static final double kPillarHeight = 0;
    public static final double kThirdLevel = 0;
    
}