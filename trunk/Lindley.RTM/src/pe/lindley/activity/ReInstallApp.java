package pe.lindley.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.util.Log;

public class ReInstallApp extends BroadcastReceiver {

	public static final String TAG = ReInstallApp.class.getSimpleName();
	
	@Override
	public void onReceive(Context arg0, Intent arg1) {
		// TODO Auto-generated method stub
		
		
		if(arg1.getDataString().compareTo("package:pe.lindley.activity")==0){
			Log.d(TAG, "REINSTALL BD");
			SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(arg0);
			Editor editor = settings.edit();
			editor.putInt("EDIT_BD", 0);
			editor.commit();
		}
	}

}
