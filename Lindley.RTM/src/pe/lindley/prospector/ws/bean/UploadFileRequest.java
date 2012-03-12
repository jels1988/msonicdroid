package pe.lindley.prospector.ws.bean;

import pe.lindley.prospector.to.FileTO;

import com.google.gson.annotations.SerializedName;

public class UploadFileRequest {

	
	@SerializedName("Fil")
	private FileTO file;



	public FileTO getFile() {
		return file;
	}


	public void setFile(FileTO file) {
		this.file = file;
	}
}
