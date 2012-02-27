package pe.lindley.red.ws.bean;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import pe.lindley.red.to.IndiceEjecucionMatrizTO;
import pe.lindley.util.ResponseBase;

public class ConsultarIndiceEjecucionMatrizResponse extends ResponseBase {

	
	
	public List<IndiceEjecucionMatrizTO> getData() {
		return data;
	}

	public void setData(List<IndiceEjecucionMatrizTO> data) {
		this.data = data;
	}

	@SerializedName("DAT")
	private List<IndiceEjecucionMatrizTO> data;
}
