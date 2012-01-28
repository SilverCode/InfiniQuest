package com.silvercode.droidz;

import android.app.Activity;
import android.util.Log;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.silvercode.driodz.model.components.Speed;
import com.silvercode.droidz.model.Droid;

public class MainGamePanel extends SurfaceView implements SurfaceHolder.Callback
{
	private static final String TAG = MainGamePanel.class.getSimpleName();
	
	private MainThread thread;
	private Droid droid;
    private String avgFps;
    
    public void setAvgFps(String avgFps)
    {
    	this.avgFps = avgFps;
    }
	
	public MainGamePanel(Context context)
	{
		super(context);		
		getHolder().addCallback(this);
		
		droid = new Droid(BitmapFactory.decodeResource(getResources(), R.drawable.droid_1), 50, 50);
		
		thread = new MainThread(getHolder(), this);
		
		setFocusable(true);
	}

	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height)
	{
		// TODO Auto-generated method stub		
	}

	public void surfaceCreated(SurfaceHolder holder)
	{
		thread.setRunning(true);
		thread.start();
	}

	public void surfaceDestroyed(SurfaceHolder holder)
	{
		boolean retry = true;
		
		while (retry)
		{
			try
			{
				thread.join();
				retry = false;
			} catch (InterruptedException e)
			{
				// try shut down the thread again
			}
		}
	}
	
	public boolean onTouchEvent(MotionEvent event)
	{
		if (event.getAction() == MotionEvent.ACTION_DOWN)
		{
			droid.handleActionDown((int)event.getX(), (int)event.getY());
			
			if (event.getY() > getHeight() - 50)
			{
				thread.setRunning(false);				
				((Activity)getContext()).finish();
			}
			else
			{
				Log.d(TAG, "Coords: x=" + event.getX() + ",y=" + event.getY());
			}
		}
		
		if (event.getAction() == MotionEvent.ACTION_MOVE)
		{
			if (droid.isTouched())
			{
				droid.setX((int)event.getX());
				droid.setY((int)event.getY());
			}
		}
		
		if (event.getAction() == MotionEvent.ACTION_UP)
		{
			if (droid.isTouched())
			{
				droid.setTouched(false);
			}
		}
		
		return true;
	}
	
	protected void render(Canvas canvas)
	{
		canvas.drawColor(Color.BLACK);
		droid.draw(canvas);
        
		displayFps(canvas, avgFps);
	}
    
	private void displayFps(Canvas canvas, String fps)
	{
        if (canvas != null && fps != null)
        {
        	Paint paint = new Paint();
        	paint.setARGB(255, 255, 255, 255);
        	canvas.drawText(fps, this.getWidth() - 50, this.getHeight() - 50, paint);
        }
	}

	public void update()
	{
        if (droid.getSpeed().getXDirection() == Speed.DIRECTION_RIGHT && droid.getX() + droid.getBitmap().getWidth() / 2 >= getWidth())
        {
            droid.getSpeed().toggleXDirection();
        }
        
        if (droid.getSpeed().getXDirection() == Speed.DIRECTION_LEFT && droid.getX() - droid.getBitmap().getWidth() / 2 <= 0)
        {
            droid.getSpeed().toggleXDirection();
        }
        
        if (droid.getSpeed().getYDirection() == Speed.DIRECTION_DOWN && droid.getY() + droid.getBitmap().getHeight() / 2 >= getHeight())
        {
            droid.getSpeed().toggleYDirection();
        }
        
        if (droid.getSpeed().getYDirection() == Speed.DIRECTION_UP && droid.getY() - droid.getBitmap().getHeight() / 2 <= 0)
        {
            droid.getSpeed().toggleYDirection();
        }
        
        droid.update();
	}
}
