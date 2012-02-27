package pe.lindley.red.ws.bean;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import pe.lindley.red.to.IndiceEjecucionAnioTO;
import pe.lindley.util.ResponseBase;

public class ConsultarIndiceEjecucionAnioResponse extends ResponseBase {

	@SerializedName("DAT")
	private List<IndiceEjecucionAnioTO> data;

	public List<IndiceEjecucionAnioTO> getData() {
		return data;
	}

	public void setData(List<IndiceEjecucionAnioTO> data) {
		this.data = data;
	}
}
