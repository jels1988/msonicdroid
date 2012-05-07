package pe.com.agrobanco.sms.service;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import com.google.gson.Gson;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

public class InvokeService {

	private static final String TAG = InvokeService.class.getName();
	
	private Context ctx;
	Gson gson;
	
	public InvokeService(Context ctx) {
		super();
		this.ctx = ctx;
		gson = new Gson();
	}
	
	public SMSResponse call(SMSData smsData) throws InvokeServiceException{
		String response=null;
		
		
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ctx);
 		int timeOutPref = Integer.parseInt(prefs.getString("time_out_servicio", "60")) * 1000;
 		String urlPref = prefs.getString("url_servicio", "") + "/Query";
 		Log.d(TAG, "Url:" + urlPref);
 	
		HttpPoster httpPoster = new HttpPoster();
		httpPoster.url=urlPref;
		httpPoster.timeOut = timeOutPref;
		httpPoster.request=gson.toJson(smsData);
		
		try {
			httpPoster.invoke();
			response=httpPoster.response;
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			Log.e(TAG,"ERROR INVOCANDO SERVICIO",e);
			throw new InvokeServiceException();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.e(TAG,"ERROR INVOCANDO SERVICIO",e);
			throw new InvokeServiceException();
		}
		
		SMSResponse SMSResponse = gson.fromJson(response, SMSResponse.class);
		return SMSResponse;
	}
}

