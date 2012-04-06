package pe.lindley.prospector.service;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import pe.lindley.prospector.negocio.ClienteBLL;
import pe.lindley.prospector.ws.service.UploadFileProxy;

import com.google.inject.Inject;

import android.content.Intent;
import android.os.IBinder;
import roboguice.service.RoboService;

public class UploadFileService extends RoboService {

	@Inject ClienteBLL clienteBLL;
	@Inject UploadFileProxy uploadFileProxy;
	
	
	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
		
		
		UploadFileTask uploadFileTask = new UploadFileTask(uploadFileProxy, clienteBLL);
		final Executor executor = Executors.newSingleThreadExecutor();
		executor.execute( uploadFileTask.future() );
		
		
	}
	
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		
		
		return null;
	}

}
