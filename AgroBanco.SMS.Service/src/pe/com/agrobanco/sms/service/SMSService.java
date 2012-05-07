package pe.com.agrobanco.sms.service;

import android.app.Activity;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.PowerManager;
import android.os.Process;
import android.preference.PreferenceManager;
import android.telephony.SmsManager;
import android.util.Log;

public class SMSService extends Service {

	private static final String SENT = "SMS_SENT";
	private static final Object mStartingServiceSync = new Object();
	private static PowerManager.WakeLock mStartingService;
	private static final String TAG = SMSService.class.getCanonicalName();
	
	private Context context;
	private ServiceHandler mServiceHandler;
	private Looper mServiceLooper;
   
	@Override
	  public void onCreate() {
	    Log.v(TAG,"SMSService: onCreate()");
	    HandlerThread thread = new HandlerThread(TAG, Process.THREAD_PRIORITY_BACKGROUND);
	    thread.start();
	    context = getApplicationContext();
	    mServiceLooper = thread.getLooper();
	    mServiceHandler = new ServiceHandler(mServiceLooper);
	  }
	
	

	@Override
	  public void onStart(Intent intent, int startId) {
	    Log.v(TAG,"SMSService: onStart()");
	    //mResultCode = intent != null ? intent.getIntExtra("result", 0) : 0;
	    Message msg = mServiceHandler.obtainMessage();
	    msg.arg1 = startId;
	    msg.obj = intent;
	    mServiceHandler.sendMessage(msg);
	  }
	
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	private final class ServiceHandler extends Handler {
		
		public ServiceHandler(Looper looper) {
		      super(looper);
		    }
		
		@Override
	    public void handleMessage(Message msg) {
	      Log.v(TAG,"SMSReceiverService: handleMessage()");
	      int serviceId = msg.arg1;
	      Intent intent = (Intent) msg.obj;
	      
	      handleSmsReceived(intent);
	      
	      finishStartingService(SMSService.this, serviceId);
		}
		
		
		private boolean smsPendientes(){
			 final Uri uriSms = Uri.parse("content://sms");
			 Cursor cursor = context.getContentResolver().query(uriSms, null,"read = 0",null, "date asc");
			 boolean rp=false;
			 if(cursor.getCount()>0)
			 {
				 rp=true;
			 }
			 cursor.close();
			 return rp;
		}
		
		private void handleSmsReceived(Intent intent) {
		    Log.v(TAG,"BEGIN handleSmsReceived");
		    
		    
		    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
	 		String sms_not_service = prefs.getString("msg_not_servicio", "");
		    
		    while(smsPendientes()){
		    	
		    
		    final ContentValues values = new ContentValues();
			values.put("read", 1);
			
			 final Uri uriSms = Uri.parse("content://sms");

			 Cursor cursor = context.getContentResolver().query(uriSms, null,"read = 0",null, "date asc");
			 
			 Log.v(TAG,"SMS:" + cursor.getCount());
			 
			 
			 SMSData smsData;
			 

			 String sms=null;
			 InvokeService invokeService = null;
			 
			 long _id =0;
			 while (cursor.moveToNext()) {
				 
				  invokeService = new InvokeService(context);
				 
				   _id = cursor.getInt(cursor.getColumnIndex("_id"));
				   smsData = new SMSData();
				   smsData._id=_id;
				   smsData.sms=cursor.getString(cursor.getColumnIndex("body"));
				   smsData.fecha=cursor.getString(cursor.getColumnIndex("date"));
				   smsData.phone=cursor.getString(cursor.getColumnIndex("address"));
				   
				   SMSResponse response = null;
				   
				   final long idSMS = _id;
				   
				   try {
						response = invokeService.call(smsData);
				   } catch (InvokeServiceException e) {
						// TODO Auto-generated catch block
						Log.e(TAG, "ERROR INVOCANDO SERVICIO",e);
						 sms = sms_not_service;
				   }
				   
				   
				   context.getContentResolver().update(uriSms, values, "_id=?",new String[]{String.valueOf(idSMS)});
				   
				   if(response!=null){
					   switch(response.status){
					   case -1:
						   sms = sms_not_service;
						   break;
					   case 0:
						   sms = response.sms;
						   break;
					   case 1:
						   continue;
					   }
				   }
				   

					PendingIntent sentPI = PendingIntent.getBroadcast(context, 0,new Intent(SENT), 0);
			        registerReceiver(new BroadcastReceiver(){
			             @Override
			             public void onReceive(Context arg0, Intent arg1) {
			                 switch (getResultCode())
			                 {
			                     case Activity.RESULT_OK:
			                    	 Log.d(TAG, "Sent");
			                    	 unregisterReceiver(this);
			                         break;
			                     case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
			                    	 Log.d(TAG, "RESULT_ERROR_GENERIC_FAILURE");
			                         break;
			                     case SmsManager.RESULT_ERROR_NO_SERVICE:
			                    	 Log.d(TAG, "RESULT_ERROR_NO_SERVICE");
			                         break;
			                     case SmsManager.RESULT_ERROR_NULL_PDU:
			                    	 Log.d(TAG, "RESULT_ERROR_NULL_PDU");
			                         break;
			                     case SmsManager.RESULT_ERROR_RADIO_OFF:
			                    	 Log.d(TAG, "RESULT_ERROR_RADIO_OFF");
			                         break;
			                 }
			             }
			         }, new IntentFilter(SENT));
			         
			         SmsManager smsManager = SmsManager.getDefault();
			         smsManager.sendTextMessage(smsData.phone, null, sms, sentPI,null);  
											   
				  
			 }
			 
			 cursor.close();
		    }
			 Log.v(TAG,"END handleSmsReceived: Intercept SMS");
		}
	}
	
	public static void beginStartingService(Context context, Intent intent) {
		 synchronized (mStartingServiceSync) {
		    	Log.v(TAG,"SMSService: beginStartingService()");
		      if (mStartingService == null) {
		        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
		        mStartingService = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, TAG);
		        mStartingService.setReferenceCounted(false);
		      }
		      mStartingService.acquire();
		      context.startService(intent);
		    }
	}

	public static void finishStartingService(Service service, int startId) {
		synchronized (mStartingServiceSync) {
		      Log.v(TAG,"SMSService: finishStartingService()");
		      if (mStartingService != null) {
		        if (service.stopSelfResult(startId)) {
		          mStartingService.release();
		        }
		      }
		    }
	}
}
