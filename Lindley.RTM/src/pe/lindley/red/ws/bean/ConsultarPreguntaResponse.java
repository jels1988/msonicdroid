package pe.lindley.red.ws.bean;

import java.util.List;

import pe.lindley.red.to.PreguntaTO;
import pe.lindley.util.ResponseBase;

import com.google.gson.annotations.SerializedName;

public class ConsultarPreguntaResponse extends ResponseBase{

	@SerializedName("DAT")
	private List<PreguntaTO> data;

	public List<PreguntaTO> getData() {
		return data;
	}

	public void setData(List<PreguntaTO> data) {
		this.data = data;
	}
}
