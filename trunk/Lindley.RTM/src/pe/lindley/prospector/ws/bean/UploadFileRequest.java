package pe.lindley.prospector.ws.bean;

import pe.lindley.prospector.to.FileTO;

import com.google.gson.annotations.SerializedName;

public class UploadFileRequest {

	@SerializedName("Cli")
	private int clienteId;
	
	
	@SerializedName("Fil")
	private FileTO file;


	public int getClienteId() {
		return clienteId;
	}


	public void setClienteId(int clienteId) {
		this.clienteId = clienteId;
	}


	public FileTO getFile() {
		return file;
	}


	public void setFile(FileTO file) {
		this.file = file;
	}
}
