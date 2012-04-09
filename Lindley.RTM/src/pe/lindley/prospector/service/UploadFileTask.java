package pe.lindley.prospector.service;


import java.io.File;
import java.util.ArrayList;


import android.content.Intent;
import android.os.Environment;
import android.util.Log;
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
			
			Log.d("UploadFileTask", "DETERNER SERVICIO");
			Intent intentService = new Intent("pe.lindley.prospector.service.uploadFileService");
			contextProvider.get().stopService(intentService);
	      
		}  
	
		public String call() throws Exception {
			// TODO Auto-generated method stub
			
			
			File path = new File( Environment.getExternalStorageDirectory(), contextProvider.get().getPackageName());
			ArrayList<FileTO> archivos = clienteBLL.listarDocumentosEnviar();
			
			Log.d("UploadFileTask", "cantidad archivos: " + String.valueOf(archivos.size()));
			
			if(archivos!=null && archivos.size()>=0){
				
				for(FileTO fileTO:archivos){
					
					File f = new File(path, fileTO.nombre);
					
					
					
					if(f.exists()){
						Log.d("UploadFileTask", "Enviando archivo:" +  fileTO.nombre);
						uploadFileProxy.fileName = fileTO.nombre;
						uploadFileProxy.filePath = f.getAbsolutePath();
						uploadFileProxy.execute();
						
					}
					
					if(uploadFileProxy.isExito()){
						
						
						if(uploadFileProxy.getResponse().getStatus()==0){
							
							if(f.exists()){
								Log.d("UploadFileTask", "Eliminando fs: " +  fileTO.nombre);
								f.delete();
							}else{
								Log.d("UploadFileTask", "No existe fs: " +  fileTO.nombre);
							}
							
							
							Log.d("UploadFileTask", "Eliminando DB archivo:" +   String.valueOf(fileTO.id));
							clienteBLL.deleteDocumento(fileTO.id);
						}
					}
					
					
				}
				
			}
		
			
			return "OK";
		}
}
