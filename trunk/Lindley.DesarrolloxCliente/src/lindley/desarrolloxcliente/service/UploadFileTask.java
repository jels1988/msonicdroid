package lindley.desarrolloxcliente.service;

import java.io.File;
import java.util.ArrayList;


import lindley.desarrolloxcliente.negocio.FotoBLL;
import lindley.desarrolloxcliente.to.FileTO;
import lindley.desarrolloxcliente.ws.service.FileUploadProxy;


import roboguice.util.RoboAsyncTask;
import android.content.Intent;
import android.os.Environment;

public class UploadFileTask extends RoboAsyncTask<String> {

	  private FileUploadProxy fileUploadProxy;
	  private FotoBLL fotoBLL;
	  
	  public UploadFileTask(FileUploadProxy fileUploadProxy,FotoBLL fotoBLL){
		this.fileUploadProxy = fileUploadProxy;
		this.fotoBLL=fotoBLL;
	  }
  
	
	  
	  
	@Override
	protected void onSuccess(String t) throws Exception {
		// TODO Auto-generated method stub
		super.onSuccess(t);
		
		Intent intentService = new Intent("lindley.desarrolloxcliente.uploadFileService");
		contextProvider.get().stopService(intentService);
      
	}




	public String call() throws Exception {
		// TODO Auto-generated method stub
		
		File path = new File( Environment.getExternalStorageDirectory(), contextProvider.get().getPackageName());
		ArrayList<FileTO> archivos = fotoBLL.Listar();
		
		if(archivos!=null && archivos.size()>=0){
			
			for(FileTO fileTO:archivos){
				
				File f = new File(path, fileTO.nombre);
				if(f.exists()){
					fileUploadProxy.fileName = fileTO.nombre;
					fileUploadProxy.filePath = f.getAbsolutePath();
					fileUploadProxy.execute();
					f.delete();
					

					if(fileUploadProxy.isExito()){
						if(fileUploadProxy.getResponse().getStatus()==0){
							fotoBLL.delete(fileTO.id);
						}
					}
					
				}
				
				
			}
			
		}
	
		
		return "OK";
	}

}
