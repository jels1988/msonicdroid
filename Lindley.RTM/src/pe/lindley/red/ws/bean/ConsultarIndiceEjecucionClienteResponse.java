package pe.lindley.red.ws.bean;

import java.util.List;

import com.google.gson.annotations.SerializedName;


import pe.lindley.red.to.IndiceEjecucionClienteTO;
import pe.lindley.util.ResponseBase;

public class ConsultarIndiceEjecucionClienteResponse extends ResponseBase {

	@SerializedName("DAT")
	private List<IndiceEjecucionClienteTO> data;

	public List<IndiceEjecucionClienteTO> getData() {
		return data;
	}

	public void setData(List<IndiceEjecucionClienteTO> data) {
		this.data = data;
	}
	
}
