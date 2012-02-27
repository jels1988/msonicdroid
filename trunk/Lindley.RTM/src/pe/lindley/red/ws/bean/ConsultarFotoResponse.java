package pe.lindley.red.ws.bean;

import com.google.gson.annotations.SerializedName;
import pe.lindley.util.ResponseBase;

public class ConsultarFotoResponse extends ResponseBase {

	
	@SerializedName("CAT")
	private String caption;
	
	@SerializedName("DAT")
	private String file;

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}
	
	/*public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	@SerializedName("DAT")
	private byte[] data;*/
}
