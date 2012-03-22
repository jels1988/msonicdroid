package pe.pae.encuesta.ws.bean;

import pe.pae.encuesta.to.FileTO;

import com.google.gson.annotations.SerializedName;

public class FileUploadRequest {

	
	@SerializedName("Fil")
	public FileTO file;
	
	
}
