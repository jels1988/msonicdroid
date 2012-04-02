package pe.lindley.prospector.ws.bean;

import pe.lindley.prospector.to.FileTO;

import com.google.gson.annotations.SerializedName;

public class UploadFileRequest {

	@SerializedName("Fil")
	public FileTO file;
	
}
