package com.course.example.progressdialogdemo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.view.KeyEvent;
import android.app.ProgressDialog;
import android.os.Handler;
import android.os.Message;
/*This is a demo of a floating Progress Dialog.
  Notice how the Handler receives information from the background thread.
 */

public class ProgressDialogDemo extends Activity {
    
	private TextView txt;    
	private ProgressDialog progDailog;
	
	private Handler handler = new Handler() {
		 @Override
		 public void handleMessage(Message msg) {
		     txt.setText("Processing Done");

		 }
		};
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        txt = (TextView) this.findViewById(R.id.text01);       
        txt.setText("Press any key to start");
    }
    
    @Override   
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		progDailog = ProgressDialog.show(this, "Progress dialog demo",
				"Working....", true);
		Thread t = new Thread(background);
		t.start();

		return true;
	}//
    
    Runnable background = new Runnable() {
    	public void run(){
    		try{
                // just doing some long operation
                Thread.sleep(5000);
           } catch (InterruptedException e) {  }
           
           finally {
        	   handler.sendEmptyMessage(0);
               progDailog.dismiss();     
           }
    	}
    	   	
    };
}