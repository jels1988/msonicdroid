package pe.lindley.prospector.service;


import java.io.File;
import java.util.ArrayList;


import android.content.Intent;
import android.os.Environment;
import pe.lindley.prospector.negocio.ClienteBLL;
import pe.lindley.prospector.to.FileTO;
import pe.lindley.prospector.ws.service.UploadFileProxy;
import roboguice.util.RoboAsyncTask;



public class UploadFileTask extends RoboAsyncTask<String> {

	
	private UploadFileProxy uploadFileProxy;
	private ClienteBLL 		clienteBLL;

	
	  public UploadFileTask(UploadFileProxy uploadFileProxy,ClienteBLL clienteBLL){
			this.uploadFileProxy = uploadFileProxy;
			this.clienteBLL = clienteBLL;
		  }
	  
	  
	  
		@Override
		protected void onSuccess(String t) throws Exception {
			// TODO Auto-generated method stub
			super.onSuccess(t);
			
			Intent intentService = new Intent("pe.lindley.prospector.service.uploadFileService");
			contextProvider.get().stopService(intentService);
	      
		}  
	
		public String call() throws Exception {
			// TODO Auto-generated method stub
			
			/*
			int icon = R.drawable.btn_ficha;
			CharSequence tickerText = "Hello";
			long when = System.currentTimeMillis();

			Notification notification = new Notification(icon, tickerText, when);
			CharSequence contentTitle = "My notification";
			CharSequence contentText = "Hello World!";
			
			notification.setLatestEventInfo(context, contentTitle, contentText, null);
			
			notificationManager.notify(HELLO_ID, notification);*/
			
			
			File path = new File( Environment.getExternalStorageDirectory(), contextProvider.get().getPackageName());
			ArrayList<FileTO> archivos = clienteBLL.listarDocumentosEnviar();
			
			if(archivos!=null && archivos.size()>=0){
				
				for(FileTO fileTO:archivos){
					
					File f = new File(path, fileTO.nombre);
					if(f.exists()){
						uploadFileProxy.fileName = fileTO.nombre;
						uploadFileProxy.filePath = f.getAbsolutePath();
						uploadFileProxy.execute();
						f.delete();

						if(uploadFileProxy.isExito()){
							if(uploadFileProxy.getResponse().getStatus()==0){
								clienteBLL.delete(fileTO.id);
							}
						}
						
					}
					
					
				}
				
			}
		
			
			return "OK";
		}
}
