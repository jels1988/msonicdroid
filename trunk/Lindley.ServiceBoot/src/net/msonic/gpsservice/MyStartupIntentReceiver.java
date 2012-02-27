package net.msonic.gpsservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyStartupIntentReceiver extends BroadcastReceiver {

	//private final String BOOT_COMPLETED_ACTION = "android.intent.action.BOOT_COMPLETED";
	
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub

		//if(intent.getAction().equals(BOOT_COMPLETED_ACTION)){
			Intent serviceIntent = new Intent();
			serviceIntent.setAction("net.msonic.MyService");
			context.startService(serviceIntent);
		//}
	}

}
