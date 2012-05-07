package pe.com.agrobanco.sms.service;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class TaskScheduledNotification extends BroadcastReceiver {

     private static final String TAG = TaskScheduledNotification.class.getName();
     
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		
		Log.d(TAG, "TaskScheduledNotification");
		
		intent.setClass(context, SMSService.class);
		intent.putExtra("result", 0);
		  
		SMSService.beginStartingService(context, intent);
	}

}
