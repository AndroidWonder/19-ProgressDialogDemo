package com.course.example.progressdialogdemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.ProgressBar;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

public class ProgressDialogDemo extends Activity {

	private TextView txt;
	private ProgressBar progress;

	private Handler handler = new Handler(Looper.getMainLooper()) {
		 @Override
		 public void handleMessage(Message msg) {
		     txt.setText("Processing Done");
			 progress.setVisibility(View.INVISIBLE);
		 }
		};
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        txt = (TextView)findViewById(R.id.text01);
		progress = (ProgressBar) findViewById(R.id.progress);

		Thread t = new Thread(background);
		t.start();
    }

    Runnable background = new Runnable() {
    	public void run(){
    		try{
                // just doing some long operation
                Thread.sleep(7000);
           } catch (InterruptedException e) {  }
           
           finally {
        	   handler.sendEmptyMessage(0);
           }
    	}
    	   	
    };
}