package pe.lindley.red.ws.bean;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import pe.lindley.red.to.SoviCoreTO;
import pe.lindley.util.ResponseBase;

public class ConsultarSoviCoreResponse extends ResponseBase {
	
	@SerializedName("DAT")
	private List<SoviCoreTO> data;

	public List<SoviCoreTO> getData() {
		return data;
	}

	public void setData(List<SoviCoreTO> data) {
		this.data = data;
	}
	
}
