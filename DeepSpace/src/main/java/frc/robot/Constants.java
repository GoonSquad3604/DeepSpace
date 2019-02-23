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

    public static final int kLeftFrontID = 20;
    public static final int kLeftRearID = 1;
    public static final int kRightFrontID = 15;
    public static final int kRightRearID = 14;

    public static final int kElevatorLeftID = 5;
    public static final int kElevatorRightID = 6;

    public static final int kIntakeControlID = 3;
    public static final int kHingeRightID = 8;
    public static final int kHingeLeftID = 7;
    
    public static final int kHatchID = 9;
    public static final int kHatchForwardBackID = 0;

    public static final int kPillarsFront = 12;
    public static final int kPillarsBack = 13;
    public static final int kPillarWheels = 2;
    /*******************/
    /*Elevator Settings*/
    /*******************/
    
    //PID Elevator
    public static final double kElevatorP = 1;
    public static final double kElevatorI = 0.0;
    public static final double kElevatorD = 0.0;
    public static final double kElevatorF = 0.0;
    public static final int kElevatorCargoVel = 950;
    public static final int kElevatorCargoAcc = 2800;

    public static final double kopenCloseP = 0.0;
    public static final double kopenCloseI = 0.0;
    public static final double kopenCloseD = 0.0;
    public static final double kopenCloseF = 0.0;

    //Rocket hatch encoder positions
    public static final double kTopRocketHatch = 0;
    public static final double kMiddleRocketHatch = 0;
    public static final double kBottomRocketHatch = 0;
    public static final double kHatchFeeder = 4800;

    //Rocket cargo encoder positions
    public static final double kTopRocketCargo = 27000;
    public static final double kMiddleRocketCargo = 14000;
    public static final double kBottomRocketCargo = 2000;
    public static final double kCargoShip = 9000;

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
    public static final double kDistanceError = 5.0;
    public static final double kHingeUp = 0.0;
    public static final double kHingeOut = 0.0;

    /**********/
    /*Commands*/
    /**********/

    public static final double kElevatorError = 0;
    public static final int kOpenHatch = 0;
    public static final int kClosedHatch = 0;
    public static final int kForwardHatch = 0;
    public static final int kBackwardHatch = 0;
    
    public static final int kReflectiveTapePipeline = 0;

    /*********/
    /*Pillars*/
    /*********/
    
    public static final double kPillarWheelDistance = 0;
    public static final double kPillarHeight = 0;
    public static final double kThirdLevel = 220;
    //public static final double kInchPerRotationPillar = 0.132040647405376;
    
    //D-Pad

    public static final int kDpadUp = 0;
    public static final int kDpadRight = 90;
    public static final int kDpadDown = 180;
    public static final int kDpadLeft = 270;
    public static final int kDpadNone = -1;
    
}