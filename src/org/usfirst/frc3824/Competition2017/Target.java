package org.usfirst.frc3824.Competition2017;

import org.usfirst.frc3824.Competition2017.Constants;

public class Target
{
	private boolean	mValid;
	private int		mFrameIndex;
	private int		mTargetType;
	private int		mXCenter;
	private int		mYCenter;
	private int    	mWidth;
	private int		mHeight;
	private int		mImgWidth;
	private int		mImgHeight;

	// ------------
	// C Structure:
	// -------------
	// struct targetInformation {
	// short frameNum;
	// short targetXInt;
	// short targetYInt;
	// short targetHeightInt;
	// short targetXDecimal;
	// short targetYDecimal;
	// short targetHeightDecimal;
	// char targetNum;
	// };

	Target()
	{
		mFrameIndex = 0;
		mTargetType = 0;
		mXCenter    = 0;
		mYCenter    = 0;
		mWidth      = 0;
		mHeight     = 0;
		mImgWidth   = 0;
		mImgHeight  = 0;
	}

	Target(byte[] udpBuf)
	{
		if (udpBuf[0] == 0)
		{
			mValid      = true;
			mTargetType =  (int) (udpBuf[ 1] & 0xFF);
			mFrameIndex = ((int) (udpBuf[ 2] & 0xFF) * 256) + ((int) (udpBuf[ 3] & 0xFF)); // treat the low byte as unsigned
			mXCenter    = ((int) (udpBuf[ 4] & 0xFF) * 256) + ((int) (udpBuf[ 5] & 0xFF));
			mYCenter    = ((int) (udpBuf[ 6] & 0xFF) * 256) + ((int) (udpBuf[ 7] & 0xFF));
			mWidth      = ((int) (udpBuf[ 8] & 0xFF) * 256) + ((int) (udpBuf[ 9] & 0xFF));
			mHeight     = ((int) (udpBuf[10] & 0xFF) * 256) + ((int) (udpBuf[11] & 0xFF));
			mImgWidth   = ((int) (udpBuf[12] & 0xFF) * 256) + ((int) (udpBuf[13] & 0xFF));
			mImgHeight  = ((int) (udpBuf[14] & 0xFF) * 256) + ((int) (udpBuf[15] & 0xFF));
		} else
		{
			mValid = false;
		}
	}
	
	Target(int targetType, int frameIndex, int xCenter, int yCenter, int height, int imgWidth, int imgHeight)
	{
		mValid      = true;
		mTargetType = targetType;
		mFrameIndex = frameIndex;
		mXCenter    = xCenter;
		mYCenter    = yCenter;
		mHeight     = height;
		mImgWidth   = imgWidth;
		mImgHeight  = imgHeight;
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
	 * Method to return the target type
	 */
	public int getTargetType()
	{
		return mTargetType;
	}

	/**
	 * Method to return the target X center
	 */
	public double getXCenter()
	{
		return mXCenter;
	}

	/**
	 * Method to return the target Y center
	 */
	public double getYCenter()
	{
		return mYCenter;
	}

	/**
	 * Method to return the target width
	 */
	public double getWidth()
	{
		return mWidth;
	}

	/**
	 * Method to return the target height
	 */
	public double getHeight()
	{
		return mHeight;
	}

	/**
	 * Method to return the image Width
	 */
	public double getImgWidth()
	{
		return mImgWidth;
	}

	/**
	 * Method to return the image height
	 */
	public double getImgHeight()
	{
		return mImgHeight;
	}
	
	public double imgXCenter() {
		return mImgWidth / 2;
	}
	
	public double imgYCenter() {
		return mImgHeight / 2;
	}
	
	public double deviationFromTarget() {
		return mXCenter - imgXCenter();
	}

	/**
	 * Method to return the distance from the specified target based on the target line length
	 * and angle of the line
	 * 
	 * Use line fit:
	 * 	line length versus target distance
	 */
	public double calculateDistanceFromTarget()
	{
		double distanceFromTarget;

		// Determine the distance from the target
		// y = Ax^2 + Bx + C
		distanceFromTarget = (Constants.DISTANCE_A * mHeight * mHeight) + 
				             (Constants.DISTANCE_B * mHeight) + 
				              Constants.DISTANCE_C;

		// Return the distance from the target
		return distanceFromTarget;
	}

	

}
