package org.usfirst.frc3824.Competition2017;

import org.usfirst.frc3824.Competition2017.Constants;

public class Target
{
	private boolean	mValid;
	private int		mFrameIndex;

	private int		mTargetAX;
	private int     mTargetAWidth;
	private int		mTargetBX;
	private int     mTargetBWidth;
	private int		mImgWidth;

	// ------------
	// C Structure:
	// -------------
	// struct targetInformation {
	// short frameNum;
	// short targetOneX;
	// short targetOneWidth;
	// short targetTwoX;
	// short targetTwoWidth;
	// short width;
	// };

	Target()
	{
		mFrameIndex   = 0;
		mTargetAX     = 0;
		mTargetAWidth = 0;
		mTargetBX     = 0;
		mTargetBWidth = 0;
		mImgWidth     = 0;
	}

	Target(byte[] udpBuf)
	{
		if (udpBuf[0] == 0)
		{
			mValid        = true;
			mFrameIndex   = ((int) (udpBuf[1] & 0xFF) * 256) + ((int) (udpBuf[2] & 0xFF)); // treat the low byte as unsigned
			mTargetAX     = ((int) (udpBuf[3] & 0xFF) * 256) + ((int) (udpBuf[4] & 0xFF));
			mTargetAWidth = ((int) (udpBuf[5] & 0xFF) * 256) + ((int) (udpBuf[6] & 0xFF));
			mTargetBX     = ((int) (udpBuf[7] & 0xFF) * 256) + ((int) (udpBuf[8] & 0xFF));
			mTargetBWidth = ((int) (udpBuf[9] & 0xFF) * 256) + ((int) (udpBuf[10] & 0xFF));
			mImgWidth     = ((int) (udpBuf[11] & 0xFF) * 256) + ((int) (udpBuf[12] & 0xFF));
		} else
		{
			mValid = false;
		}
	}
	
	Target(int frameIndex, int targetAX, int targetAWidth, int targetBX, int targetBWidth, int imgWidth, int imgHeight)
	{
		mValid        = true;
		mFrameIndex   = frameIndex;
		mTargetAX     = targetAX;
		mTargetAWidth = targetAWidth;
		mTargetBX     = targetBX;
		mTargetBWidth = targetBWidth;
		mImgWidth     = imgWidth;
	}


	/**
	 * Method to return if the target data is valid
	 */
	public boolean isValid()
	{
		return mValid;
	}

	/**
	 * Method to return the camera frame index
	 */
	public int getFrameIndex()
	{
		return mFrameIndex;
	}

	/**
	 * Method to return the target A X value
	 */
	public double getTargetAX()
	{
		return mTargetAX;
	}
	
	/**
	 * Method to return the target A X value
	 */
	public double getTargetAWidth()
	{
		return mTargetAWidth;
	}
	
	/**
	 * Method to return the target A X value
	 */
	public double getTargetBX()
	{
		return mTargetBX;
	}

	/**
	 * Method to return the target height
	 */
	public double getTargetBWidth()
	{
		return mTargetBWidth;
	}

	/**
	 * Method to return the image Width
	 */
	public double getImgWidth()
	{
		return mImgWidth;
	}
	
	public double imgXCenter() {
		return mImgWidth / 2;
	}
	
	public double deviationFromTarget() {
		return mTargetAX - imgXCenter();
	}
}
