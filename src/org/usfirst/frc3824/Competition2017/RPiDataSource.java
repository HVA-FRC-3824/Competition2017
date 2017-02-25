package org.usfirst.frc3824.Competition2017;

import java.lang.Thread;
import java.net.*;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.io.*;

/*
 * RPiDataSource is an object representing the RaspberryPi as a source of
 * Data for the RoboRio.  The connection to the RPi is via a network connection.
 * This object will handle all of the networking and cache values locally
 */
public class RPiDataSource
{
	private DatagramSocket	socket		= null;
	private int				bufLength	= 256;
	private boolean			running		= false;
	private boolean			mActive		= false;
	private Target			lastTarget;

	private static RPiDataSource instance = new RPiDataSource();

	/**
	 * Private Constructor to support a singleton object
	 */
	private RPiDataSource()
	{

	}

	/**
	 * Method to return the RPiDataSource class 
	 */
	public static RPiDataSource getInstance()
	{
		return instance;
	}

	/**
	 * Start the RaspberryPi connection as a thread
	 */
	public boolean start()
	{
		// Start a thread for the RaspberryPi connection
		new Thread(null, () ->
		{
			server();
		}, "RpiDataSource").start();

		return true;
	}

	/*
	 * This is the server connection to the RaspberryPi
	 */
	private void server()
	{
		try
		{
			socket = new DatagramSocket(5800);
			socket.setSoTimeout(500);  // receive packet will timeout if no packet is received for 500ms
			System.out.println("RPiDataSource listening on port: " + socket.getLocalPort());
			
		} catch (java.io.IOException e)
		{
			System.err.println("Could not create datagram socket.");
			return;
		}

		// Indicate that the connection is running
		running = true;

		while (running)
		{
			try
			{
				byte[]         buffer = new byte[bufLength];
				int            port;
				DatagramPacket packet;
				InetAddress    address;
				
				// Receive a packet of data
				packet = new DatagramPacket(buffer, bufLength);
				
				try
				{
					socket.receive(packet);
					address = packet.getAddress();
					port    = packet.getPort();

					System.out.println("Received data from IP address: " + address + " port: " + port);

					// Store the data locally
					lastTarget = new Target(buffer);

					mActive = true;
					// send a response - not wanting to send a response at the moment
					// packet = new DatagramPacket(buf, buf.length, address, port);
					// socket.send(packet);
				} catch (SocketTimeoutException e)
				{
					mActive = false;
				}
			} catch (IOException e)
			{
				System.err.println("IOException: " + e);
				running = false;
			}
		}

		// Close the RaspberryPi connection
		socket.close();
	}

	/**
	 * Method to return the last target data packet
	 */
	public Target getTarget()
	{
		return lastTarget;
	}

	/**
	 * Method to indicate if the connection to the RaspberryPi is active
	 */
	public boolean isActive()
	{
		return mActive;
	}

	/**
	 * Method to update the SmartDashboard with the target information
	 */
	public void updateSmartDashboardData()
	{
		try
		{
			// Determine if the target information is valid
			if (getTarget().isValid())
			{
				// Send the target information to the SmartDashboard
				SmartDashboard.putString("Target",
						new String("Idx: " + getTarget().getFrameIndex() + 
								"RPITargetAX: " + getTarget().getTargetAX() +
								"RPITargetAWidth: " + getTarget().getTargetAWidth() +
								"RPITargetBX: " + getTarget().getTargetBX() +
								"RPITargetBWidth: " + getTarget().getTargetBWidth()));
			} else
			{
				SmartDashboard.putString("Target", "NO VALID TARGET");
			}
		} catch (Exception e)
		{

		}
	}

	/**
	 * Method to indicate if the RaspberryPi connection is active
	 */
	public void updateSmartDashboardActive()
	{
		try
		{
			SmartDashboard.putBoolean("RPiActive", isActive());
		} catch (Exception e)
		{

		}
	}
}
