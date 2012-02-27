package pe.lindley.exmedia.ws.bean;

import com.google.gson.annotations.SerializedName;

public class ConsultarFilesRequest {
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	@SerializedName("FLN")
	private String fileName;
	
	@SerializedName("TIP")
	private int tipo;
}
