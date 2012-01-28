package com.silvercode.droidz.model;

import android.graphics.Bitmap;
import android.graphics.Rect;

public class ElaineAnimated
{
    private static final String TAG = ElaineAnimated.class.getSimpleName();
    
    private Bitmap bitmap;
    private Rect sourceRect;
    private int frameNr;
    private int currentFrame;
    private long frameTicker;
    private int framePeriod;
    
    private int spriteWidth;
    private int spriteHeight;
    
    private int x;
    private int y;
    
    public ElaineAnimated(Bitmap bitmap, int x, int y, int width, int height, int fps, int frameCount)
    {
        this.bitmap = bitmap;
        this.x = x;
        this.y = y;
        this.spriteWidth = bitmap.getWidth() / frameCount;
        this.spriteHeight = bitmap.getHeight();
        this.sourceRect = new Rect(0, 0, spriteWidth, spriteHeight);
        this.currentFrame = 0;
        this.frameNr = frameCount;
        this.framePeriod = 1000 / fps;
        this.frameTicker = 0l;
    }
    
    public void update(long gameTime)
    {
    	if (gameTime > frameTicker + framePeriod)
    	{
    		frameTicker = gameTime;
    		
    		currentFrame++;
    		
    		if (currentFrame >= frameNr)
    		{
    			currentFrame = 0;
    		}
    	}
        
    	this.sourceRect.left = currentFrame * spriteWidth;
    	this.sourceRect.right = this.sourceRect.left + spriteWidth;
    }
}
