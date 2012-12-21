package lindley.desarrolloxcliente.ws.bean;


import lindley.desarrolloxcliente.to.FileTO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FileUploadRequest {

	
	@Expose()
	@SerializedName("Fil")
	public FileTO file;
	
	
}
