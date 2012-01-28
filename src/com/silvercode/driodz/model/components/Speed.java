package com.silvercode.driodz.model.components;

public class Speed
{
    public static final int DIRECTION_RIGHT = 1;
    public static final int DIRECTION_LEFT = -1;
    public static final int DIRECTION_UP = -1;
    public static final int DIRECTION_DOWN = 1;
    
    private float xv = 2; // x velocity
    private float yv = 2; // y velocity
    
    private int xDirection = DIRECTION_RIGHT;
    private int yDirection = DIRECTION_DOWN;
    
    public Speed()
    {
    	this.xv = 2;
    	this.yv = 2;
    }
    
    public Speed(float xv, float yv)
    {
    	this.xv = xv;
    	this.yv = yv;
    }
    
    public float getXv()
    {
    	return xv;
    }
    
    public float getYv()
    {
    	return yv;
    }
    
    public void setXv(float xv)
    {
    	this.xv = xv;
    }
    
    public void setYv(float yv)
    {
    	this.yv = yv;
    }
    
    public int getXDirection()
    {
    	return xDirection;
    }
    
    public int getYDirection()
    {
    	return yDirection;
    }
    
    public void setXDirection(int xDirection)
    {
    	this.xDirection = xDirection;
    }
    
    public void setYDirection(int yDirection)
    {
    	this.yDirection = yDirection;
    }
    
    // Changes the direction on the X Axis
    public void toggleXDirection()
    {
    	xDirection = xDirection * -1;
    }
    
    // Changes the direction on the Y Axis
    public void toggleYDirection()
    {
    	yDirection = yDirection * -1;
    }
}
