package pe.lindley.exmedia.ws.bean;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import pe.lindley.exmedia.to.FileTO;
import pe.lindley.util.ResponseBase;

public class ConsultarFilesResponse extends ResponseBase {
	@SerializedName("FIS")
	private List<FileTO> files;

	public List<FileTO> getFiles() {
		return files;
	}

	public void setFiles(List<FileTO> files) {
		this.files = files;
	}
	
}
