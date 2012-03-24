package pe.pae.encuesta.service;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import pe.pae.encuesta.negocio.RespuestaBLL;
import pe.pae.encuesta.ws.service.FileUploadProxy;

import com.google.inject.Inject;

import android.content.Intent;
import android.os.IBinder;
import roboguice.service.RoboService;

public class UploadFileService extends RoboService {

	@Inject FileUploadProxy fileUploadProxy;
	@Inject RespuestaBLL respuestaBLL;
	  
	
	
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
		

		UploadFileTask uploadFileTask = new UploadFileTask(fileUploadProxy, respuestaBLL);
		final Executor executor = Executors.newSingleThreadExecutor();
		executor.execute( uploadFileTask.future() );
		
		
	}
	
	
	

}
