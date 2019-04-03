package frc.robot;
import edu.wpi.first.wpilibj.I2C;

public class LIDAR{
	    
    private I2C m_i2c;
    private byte[] buffer;
	
	public LIDAR() {  
    	m_i2c = new I2C(I2C.Port.kMXP,0x62);
    	initLidar();     	          	  
    }
    
	private void initLidar()
	{		
		m_i2c.write(0x00, 0x04);
		buffer = new byte[2];
	}

	public int getDistance()
	 {
		m_i2c.read(0x0f, 2, buffer);

		return (int)Integer.toUnsignedLong(buffer[0] << 8) + Byte.toUnsignedInt(buffer[1]);	
	}
}