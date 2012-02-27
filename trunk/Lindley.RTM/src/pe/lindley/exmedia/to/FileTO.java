package pe.lindley.exmedia.to;

import com.google.gson.annotations.SerializedName;

public class FileTO {

	@SerializedName("FLN")
	private String fileName;
	
	@SerializedName("URL")
	private String url;
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	@SerializedName("TIP")
	private int tipo;
	

}
