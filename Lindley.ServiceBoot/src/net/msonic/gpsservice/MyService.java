package net.msonic.gpsservice;

import java.util.Timer;
import java.util.TimerTask;

import net.msonic.gps.ws.service.GPSServiceProxy;
import roboguice.service.RoboService;
import com.google.inject.Inject;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.telephony.TelephonyManager;
import android.util.Log;

public class MyService extends  RoboService {

	private static final String TAG = MyService.class.getSimpleName();
	//private static final long MINIMUM_DISTANCE_CHANGE_FOR_UPDATES = 1; // in Meters
    //private static final long MINIMUM_TIME_BETWEEN_UPDATES =  1000 * 60 * 2; // in Milliseconds

    protected LocationManager locationManager;
    protected String deviceId;
	@Inject GPSServiceProxy gpsServiceProxy;
	
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	private void toggleGPS(boolean enable) {
		
		
	    String provider = Settings.Secure.getString(getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);

	    if(provider.contains("gps") == enable) {
	        return; // the GPS is already in the requested state
	    }

	    final Intent poke = new Intent();
	    poke.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");
	    poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
	    poke.setData(Uri.parse("3"));
	    getApplicationContext().sendBroadcast(poke);
	}*/
	
	
	TimerTask timerTask;
	Timer t = new Timer();
	final Handler handler = new Handler();
	@Override
	public void onCreate() {
		
		
		TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
		
	 	deviceId = telephonyManager.getDeviceId();
		
	 	final LocationListener locationListener = new LocationListener() {
	 		int noOfFixes=0;
	 		
			@Override
			public void onStatusChanged(String provider, int status, Bundle extras) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProviderEnabled(String provider) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProviderDisabled(String provider) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onLocationChanged(Location location) {
				// TODO Auto-generated method stub
				Log.v(TAG, "ES NULO" + String.valueOf(gpsServiceProxy==null));
				
				
				noOfFixes++;

				
				Log.v(TAG, "INICIO WS AHORA");
				
				
				gpsServiceProxy.setLatitud(String.valueOf(location.getLatitude()));
				gpsServiceProxy.setLongitud(String.valueOf(location.getLongitude()));
				gpsServiceProxy.setFixes(String.valueOf(noOfFixes));
				gpsServiceProxy.setAltitud(String.valueOf(location.getAltitude()));
				gpsServiceProxy.setAccuracy(String.valueOf(location.getAccuracy()));
				gpsServiceProxy.setTimeStamp(String.valueOf(location.getTime()));
				gpsServiceProxy.setTelefono(deviceId);
				
				try{
					gpsServiceProxy.execute();
				}catch(Exception ex){
					Log.v(TAG, "INICIO WS AHORA");
				}finally{
					Log.v(TAG, "LLAMANDO WS FIN");
				}
			}
		};
	 	
	 	
		timerTask = new TimerTask() {
			
			
			@Override
			public void run() {
				
			 	
				// TODO Auto-generated method stub
				
				handler.post(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						if(deviceId==null || deviceId.compareTo("")==0) return;
						
						//toggleGPS(true);
						
						 
						locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
						
						
						if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
							locationManager.requestLocationUpdates(
					        		LocationManager.NETWORK_PROVIDER,
					        		0L, 
					        		0f,
					                locationListener);
					        
						 }
						
					}
				});
		        
			}
		};
		
		t.schedule(timerTask, 500, 1000 * 60 * 5);
	
	   
		 // TODO Auto-generated method stub
		 		super.onCreate();
		 	
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.d(TAG, "msonic:destroy");

	}

	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
		
	    
	}




	
}


/*
class MyLocationListener implements LocationListener {
	
	private static final String TAG = "MyLocationListener";
	
	int noOfFixes=0;
	
	public GPSServiceProxy gpsServiceProxy = new GPSServiceProxy();
	
	
	public String deviceId="13432";
	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		
		
		Log.v(TAG, "ES NULO" + String.valueOf(gpsServiceProxy==null));
		
		gpsServiceProxy.setProxyListener(new ProxyListener<GpsPosReponse>() {
			@Override
			public void onFinish(GpsPosReponse arg0) {
				// TODO Auto-generated method stub
				Log.v(TAG, "LLAMANDO WS FIN");
			}
		});
		
		noOfFixes++;

		
		Log.v(TAG, "INICIO WS");
		
		gpsServiceProxy.setLatitud(String.valueOf(location.getLatitude()));
		gpsServiceProxy.setLongitud(String.valueOf(location.getLongitude()));
		gpsServiceProxy.setFixes(String.valueOf(noOfFixes));
		gpsServiceProxy.setAltitud(String.valueOf(location.getAltitude()));
		gpsServiceProxy.setAccuracy(String.valueOf(location.getAccuracy()));
		gpsServiceProxy.setTimeStamp(String.valueOf(location.getTime()));
		gpsServiceProxy.setTelefono(deviceId);
		
		gpsServiceProxy.executeAsyn();
		
		
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}

}*/

