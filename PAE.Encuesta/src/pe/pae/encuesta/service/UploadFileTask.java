package pe.pae.encuesta.service;

import java.io.File;
import java.util.ArrayList;

import pe.pae.encuesta.negocio.RespuestaBLL;
import pe.pae.encuesta.to.FileTO;
import pe.pae.encuesta.ws.service.FileUploadProxy;

import android.content.Intent;
import android.os.Environment;


import roboguice.util.RoboAsyncTask;

public class UploadFileTask extends RoboAsyncTask<String> {

	  private FileUploadProxy fileUploadProxy;
	  private RespuestaBLL respuestaBLL;
	
	
	  public UploadFileTask(FileUploadProxy fileUploadProxy,RespuestaBLL respuestaBLL){
		this.fileUploadProxy = fileUploadProxy;
		this.respuestaBLL = respuestaBLL;
	  }
    
	
	  
	  
	@Override
	protected void onSuccess(String t) throws Exception {
		// TODO Auto-generated method stub
		super.onSuccess(t);
		
		Intent intentService = new Intent("pae.service.uploadFileService");
		contextProvider.get().stopService(intentService);
        
	}




	public String call() throws Exception {
		// TODO Auto-generated method stub
		
		File path = new File( Environment.getExternalStorageDirectory(), contextProvider.get().getPackageName());
		ArrayList<FileTO> archivos = respuestaBLL.listarDocumentos();
		
		if(archivos!=null && archivos.size()>=0){
			
			for(FileTO fileTO:archivos){
				
				File f = new File(path, fileTO.nombre);
				if(f.exists()){
					fileUploadProxy.fileName = fileTO.nombre;
					fileUploadProxy.filePath = f.getAbsolutePath();
					fileUploadProxy.execute();
					f.delete();
				}
			}
			
		}
	
		
		return "OK";
	}

}
