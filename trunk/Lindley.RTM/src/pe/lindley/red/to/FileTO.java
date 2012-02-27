package pe.lindley.red.to;

import com.google.gson.annotations.SerializedName;

public class FileTO {
	
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
}
