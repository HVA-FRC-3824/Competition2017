package org.usfirst.frc3824.Competition2017.utils;
import edu.wpi.first.wpilibj.I2C;


public class LEDChooser extends I2C{
	
	public final int	OFF							= 0;
	public final int	SOLID_BLUE 					= 1;
	public final int	SOLID_RED					= 2;
	public final int	FLASH_ALTERNATE_RED_BLUE	= 3;
	public final int	RAINBOW						= 4;
	
	private static Port m_port;
	private static int m_deviceAddress;
	private static LEDChooser instance = null;
	
	public static void config(Port port, int deviceAddress){
		instance = null;
		m_port = port;
		m_deviceAddress = deviceAddress;
		
	}
	
	public static LEDChooser getInstance(Port port, int deviceAddress){
		if(instance == null){
			instance = new LEDChooser(m_port, m_deviceAddress);
		}
		return instance;
	}
	
	private LEDChooser(Port port, int deviceAddress) {
		super(port, deviceAddress);
		m_deviceAddress = deviceAddress;
	}
		
	public void setPattern(int patNum) {

		byte[] data = new byte[1];
		
		if( (patNum >= 0) && (patNum <= 255) )
		{
			data[0] = (byte) (patNum & 0xFF);
			writeBulk(data);
		}
	}

	public void off()
	{
		setPattern(OFF);
	}
	
}
