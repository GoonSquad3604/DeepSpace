
package frc.vision;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Limelight {
    private NetworkTable table; //The network table that the limelight broadcasts its data to
    private NetworkTableEntry tx;   //Target x
    private NetworkTableEntry ty;   //Target y
    private NetworkTableEntry ta;   //Target area
    private NetworkTableEntry tv;   //Target exists
    private NetworkTableEntry ts;   //Target skew
    private NetworkTableEntry thoriz;   //Target width
    private NetworkTableEntry tvert;   //Target width

    //Inintializes all variables
    @Deprecated
    public Limelight()
    {
        table = NetworkTableInstance.getDefault().getTable("limelight");
        doSetup();
    }
    public Limelight(String name)
    {
        table = NetworkTableInstance.getDefault().getTable(name);
        doSetup();
    }
    private void doSetup()
    {
        tx = table.getEntry("tx");
        ty = table.getEntry("ty");
        ta = table.getEntry("ta");
        tv = table.getEntry("tv");
        ts = table.getEntry("ts");
        thoriz = table.getEntry("thoriz");
        tvert = table.getEntry("tvert");
    }
    //Returns the target X value from the limelight
    public double getTargetX()
    {
        return tx.getDouble(0.0);
    }
    //Returns the target Y value from the limelight
    public double getTargetY()
    {
        return ty.getDouble(0.0);
    }
    //Returns the target area from the limelight
    public double getTargetArea()
    {
        return ta.getDouble(0.0);
    }
    //Returns the target skew from the limelight
    public double getTargetSkew()
    {
        return ts.getDouble(0.0);
    }
    //Returns the width of the bounding box
    public double getTargetWidth()
    {
        return thoriz.getDouble(0.0);
    }
    //Returns the height of the bounding box
    public double getTargetHeight()
    {
        return tvert.getDouble(0.0);
    }
    //Returns if the target is in fact on the screen
    public boolean doesTargetExist()
    {
        if(tv.getDouble(0.0)<0.1)
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "LIMELIGHT DATA STARTS HERE: tx="+getTargetX()+" :: ty=" + getTargetY() + " :: ts=" + getTargetSkew() 
        + "\n thoriz=" + getTargetWidth() + " :: tvert=" + getTargetHeight(); 
    }
}
//"We are in school, nothing is consentual." -- Tony