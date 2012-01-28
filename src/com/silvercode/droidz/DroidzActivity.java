package com.silvercode.droidz;

import android.app.Activity;
import android.util.Log;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class DroidzActivity extends Activity {
    /** Called when the activity is first created. */
	
	private static final String TAG = DroidzActivity.class.getSimpleName();
	
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(new MainGamePanel(this));
        Log.d(TAG, "View Added...");
    }
    
    @Override
    protected void onDestroy()
    {
    	Log.d(TAG, "Destroying");
    	super.onDestroy();
    }
    
    @Override
    protected void onStop()
    {
    	Log.d(TAG, "Stopping..");
    	super.onStop();
    }
}