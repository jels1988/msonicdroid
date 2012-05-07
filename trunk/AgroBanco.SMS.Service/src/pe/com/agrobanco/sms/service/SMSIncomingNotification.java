package pe.com.agrobanco.sms.service;

import java.util.Calendar;

import pe.edu.agrobanco.sms.service.R;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

public class SMSIncomingNotification extends BroadcastReceiver {

	 private static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
     private static final String TAG = SMSIncomingNotification.class.getName();
     
	@Override
	public void onReceive(Context ctx, Intent intent) {
		// TODO Auto-generated method stub
		
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ctx);
		boolean servicioEnabled = prefs.getBoolean("sms_servicio_onoff", false);
		Log.i(TAG, "=======================================");
		if(!servicioEnabled){
			Log.i(TAG, "Service SMS disabled.");
		}else{
			
			String action = intent.getAction();
			
			Log.i(TAG, "Intent recieved: " + action);
			
			if(SMS_RECEIVED.compareTo(action)==0){
				
			  Log.i(TAG, "Scheduling Service");
			  
			  Calendar cal = Calendar.getInstance();
			  cal.add(Calendar.SECOND, ctx.getResources().getInteger(R.integer.mseconds_task));
			  Intent intentNotification = new Intent(ctx, TaskScheduledNotification.class);
			  
			  PendingIntent sender = PendingIntent.getBroadcast(ctx, 0, intentNotification, PendingIntent.FLAG_UPDATE_CURRENT);
			   
			  AlarmManager am = (AlarmManager) ctx.getSystemService(Activity.ALARM_SERVICE);
			  am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), sender);
			  
			  Log.i(TAG, "Scheduled Service");
			  
			}
		}
		
		Log.i(TAG, "======================================="); 
		
		
	}

}
