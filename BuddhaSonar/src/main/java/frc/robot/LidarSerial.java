/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot;

import java.nio.*; 
import edu.wpi.first.wpilibj.SerialPort;

/**
 * Setups the usb port to be used as a serial connection to get Lidar Sensor data from an arduino
 */
public class LidarSerial {

  private SerialPort serial; 

  /**
    * sets up the USB port to be used as serial input from Arduino with a Lidar Sensor
    */
  public LidarSerial()
  {
    serial = new SerialPort(9600, SerialPort.Port.kUSB);
  }
  /**
   * reads serial input from arduino and converts it to an int. This distance is in centimeters
   */
  public int getCentimeters()
  {
    byte[] bytes = serial.read(2);
    int centimeters = ByteBuffer.wrap(bytes).getInt();
    return centimeters; 
  }

  /**
   * returns distance in Inches instead of centimeters because this is America
   */
  public double getInches()
  {
    return getCentimeters()/2.54; 
  }
}