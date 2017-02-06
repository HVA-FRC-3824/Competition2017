package org.usfirst.frc3824.Competition2017;

public class Target {
	private boolean mValid;
	private int mFrameIndex;
	private int mTargetType;
	private int mXCenter;
	private int mYCenter;
	private int mHeight;
	private int mImgWidth;
	private int mImgHeight;
	
	// ------------
	// C Structure:
	//-------------
	// struct targetInformation {
	//		short frameNum;
	//		short targetXInt;
	//		short targetYInt;
	//		short targetHeightInt;
	//		short targetXDecimal;
	//		short targetYDecimal;
	//		short targetHeightDecimal;
	//		char  targetNum;
	//	};
	
	
	Target()
	{
		mFrameIndex = 0;
		mTargetType = 0;
		mXCenter = 0;
		mYCenter = 0;
		mHeight = 0;
		mImgWidth = 0;
		mImgHeight = 0;
	}
	
	Target(byte[] udpBuf)
	{
		if(udpBuf[0] == 0)
		{
			mValid       = true;
			mTargetType  =  (int) (udpBuf[ 1] & 0xFF);
			mFrameIndex  = ((int) (udpBuf[ 2] & 0xFF) * 256) + ((int) (udpBuf[ 3] & 0xFF));	// treat the low byte as unsigned
			mXCenter     = ((int) (udpBuf[ 4] & 0xFF) * 256) + ((int) (udpBuf[ 5] & 0xFF));
			mYCenter     = ((int) (udpBuf[ 6] & 0xFF) * 256) + ((int) (udpBuf[ 7] & 0xFF));
			mHeight      = ((int) (udpBuf[ 8] & 0xFF) * 256) + ((int) (udpBuf[ 9] & 0xFF));
			mImgWidth    = ((int) (udpBuf[10] & 0xFF) * 256) + ((int) (udpBuf[11] & 0xFF));
			mImgHeight   = ((int) (udpBuf[12] & 0xFF) * 256) + ((int) (udpBuf[13] & 0xFF));
		}
		else
		{
			mValid       = false;
		}
	}
	
	public boolean isValid()
	{
		return mValid;
	}
		
	public int getFrameIndex()
	{
		return mFrameIndex;
	}
	
	public int getTargetType()
	{
		return mTargetType;
	}
	
	public double getXCenter()
	{
		return mXCenter;
	}
	
	public double getYCenter()
	{
		return mYCenter;
	}
	
	public double getHeight()
	{
		return mHeight;
	}
	public double getImgWidth()
	{
		return mImgWidth;
	}
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


}
