package frc.robot;

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
    
    //PID
    public static final double kElevatorP = 0.0;
    public static final double kElevatorI = 0.0;
    public static final double kElevatorD = 0.0;
    public static final double kElevatorF = 0.0;

    //Rocket hatch encoder positions
    public static final int kBottomRocketHatch = 0;
    public static final int kMiddleRocketHatch = 0;
    public static final int kTopRocketHatch = 0;

    //Rocket cargo encoder positions
    public static final int kBottomRocketCargo = 0;
    public static final int kMiddleRocketCargo = 0;
    public static final int kTopRocketCargo = 0;

}