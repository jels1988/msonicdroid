package lindley.desarrolloxcliente.service;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import lindley.desarrolloxcliente.negocio.FotoBLL;
import lindley.desarrolloxcliente.ws.service.FileUploadProxy;


import roboguice.service.RoboService;
import android.content.Intent;
import android.os.IBinder;

import com.google.inject.Inject;

public class UploadFileService extends RoboService {

	@Inject FileUploadProxy fileUploadProxy;
	@Inject FotoBLL fotoBLL;
	  
	
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		
		
		
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		
		
		
		return null;
	}

	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
		

		UploadFileTask uploadFileTask = new UploadFileTask(fileUploadProxy, fotoBLL);
		final Executor executor = Executors.newSingleThreadExecutor();
		executor.execute( uploadFileTask.future() );
		
		
	}
	
	
	

}
