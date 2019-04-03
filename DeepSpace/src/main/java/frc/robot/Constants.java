package frc.robot;

public class Constants
{

    /*********/
    /*General*/
    /*********/

    public static boolean isABot = true;
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
    public static final int kElevatorCargoVel = 1350;
    public static final int kElevatorCargoAcc = 2400;

    //Rocket hatch encoder positions
    public static final double kTopRocketHatch = 27500;
    public static final double kMiddleRocketHatch = 18500;
    public static final double kBottomRocketHatch = 4500;
    public static final double kHatchFeeder = 4500;
    public static final double kHatchFeederUp = 6500;

    //Rocket cargo encoder positions
    public static final double kTopRocketCargo = 26500;
    public static final double kMiddleRocketCargo = 14000;
    public static final double kBottomRocketCargo = 1500;
    public static final double kCargoShip = 8750;

    public static final int kElevatorLimitSensorID = 0;

    /*************/
    /*Drive Train*/
    /*************/

    public static final double kDriveP = 0;
    public static final double kDriveI = 0;
    public static final double kDriveD = 0;
    public static final double kVelocityRatio = 0;
    public static final double kAccelerationRatio = 0;
    public static final double kRobotWidth = 0.5476875; //Meters
    public static final double kWheelDiameter = 6.0; //Inches
    public static final double kGearRatio = 10.12; //Number of motor revs per wheel rev
    public static final double kDt = 0;
    public static final double kVelocity = 0;
    public static final double kAcceleration = 0;
    public static final double kJerk = 0;

    //Turn PID
    public static final double kTurnP = 0.08;
    public static final double kTurnI = 0.0;
    public static final double kTurnD = 0.0;


    /*******/
    /*Auton*/
    /*******/

    public static final double kTurnError = 1.0; //Acceptable error (degrees) before ending turn
    public static final double kDistanceError = 5.0;
    public static final double kHingeUp = 0.0;
    public static final double kHingeOut = 0.0;
    public static final int kReflectiveTapePipeline = 0;

    /*********/
    /*Pillars*/
    /*********/
    
    public static final double kThirdLevel = 153;
    public static final double kSecondLevel = 53;
    //public static final double kInchPerRotationPillar = 0.132040647405376;
    
    //D-Pad

    public static final int kDpadUp = 0;
    public static final int kDpadRight = 90;
    public static final int kDpadDown = 180;
    public static final int kDpadLeft = 270;
    public static final int kDpadNone = -1;
    
    /*******/
    /*Cargo*/
    /*******/

    public static final double kHingeError = 4; //(Degrees)
    public static int sensorAt0 = 524;
    public static int sensorAt90 = 739;

    //B Bot
    // public static int sensorAt0 = 848;
    // public static int sensorAt90 = 642;

    /*******/
    /*Hatch*/
    /*******/
    public static final int kHatchMovementPlace = 1500;
    public static final int kHatchMovementPickUp = 2000;
    public static final int kArticulatorIn = 200;
    public static final int kArticulatorOut = 2800;
}