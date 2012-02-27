package pe.lindley.red.ws.bean;

import java.util.List;

import pe.lindley.red.to.GeneradorTO;
import pe.lindley.util.ResponseBase;

import com.google.gson.annotations.SerializedName;

public class ConsultarGeneradorResponse extends ResponseBase{

	@SerializedName("DAT")
	private List<GeneradorTO> data;

	public List<GeneradorTO> getData() {
		return data;
	}

	public void setData(List<GeneradorTO> data) {
		this.data = data;
	}
	
}
