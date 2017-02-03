package org.usfirst.frc3824.Competition2017;
import java.lang.Thread;
import java.net.*;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.io.*;

/*
 * RPiDataSource is an object representing the RaspberryPi as a source of
 * Data for the RoboRio.  The connection to the RPi is via a network connection.
 * This object will handle all of the netowrking and cache values locally
 */
public class RPiDataSource {
	private	DatagramSocket socket = null;
	private int bufLength = 256;
	private boolean running = false;
	private Target lastTarget;
	private boolean mActive = false;
	
	/*
	 * Private Constructor to support a singleton object
	 */
	private RPiDataSource() {
		
	}
	
	private static RPiDataSource instance = new RPiDataSource();
	public static RPiDataSource getInstance() {return instance;}
	
	
	public boolean start() {
		new Thread(null, ()->{
			server();
		}, "RpiDataSource").start();
		
		return true;
	}
	
	/*
	 * This is the server connection to the RaspberryPi
	 */
	private void server(){
		
		try{
			socket = new DatagramSocket(5800);
			socket.setSoTimeout(500);    // receive packet will timeout if no packet is received for 500ms
			System.out.println("RPiDataSource listening on port: " + socket.getLocalPort());
		} catch (java.io.IOException e) {
			System.err.println("Could not create datagram socket.");
			return;
		}

		running = true;
		
		while(running) {
			try {
				byte[] buf = new byte[bufLength];
				DatagramPacket packet;
				InetAddress address;
				int port;
				
				// receive a packet of data
				packet = new DatagramPacket(buf, bufLength);
				try
				{
					socket.receive(packet);
					address = packet.getAddress();
					port = packet.getPort();
					
					System.out.println("Received data from IP address: " + address + " port: " + port);
					
					// Store the data locally
					lastTarget = new Target(buf);
					
					mActive = true;
					// send a response - not wanting to send a response at the moment
					// packet = new DatagramPacket(buf, buf.length, address, port);
					// socket.send(packet);
				}
				catch (SocketTimeoutException e)
				{
					mActive = false;
				}
			} catch (IOException e) {
				System.err.println("IOException:  " + e);
				running = false;
			}
		}
		
		socket.close();
	}
	
	public Target getTarget()
	{
		return lastTarget;
	}
	
	public boolean isActive()
	{
		return mActive;
	}
	
	public void updateSmartDashboardData()
	{
        try {
        	if(getTarget().isValid()){
		        SmartDashboard.putString("Target", new String("Idx: " + getTarget().getFrameIndex() 
						+ "  Type: " + getTarget().getTargetType()
						+ "  Center: "  + getTarget().getXCenter() + ", "  + getTarget().getYCenter()
						+ "  Height: "  + getTarget().getHeight()) );
        	}
        	else {
        		SmartDashboard.putString("NO VALID TARGET", null);
        	}
        }
        catch (Exception e) {
        	
        }		
	}
	
	public void updateSmartDashboardActive()
	{
		try {
			SmartDashboard.putBoolean("RPiActive", isActive());
		}
		catch (Exception e) {
			
		}
	}
}


