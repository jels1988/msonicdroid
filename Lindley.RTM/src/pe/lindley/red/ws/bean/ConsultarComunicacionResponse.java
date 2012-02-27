package pe.lindley.red.ws.bean;

import java.util.List;
import pe.lindley.red.to.ComunicacionTO;
import pe.lindley.util.ResponseBase;

import com.google.gson.annotations.SerializedName;

public class ConsultarComunicacionResponse extends ResponseBase{

	@SerializedName("DAT")
	private List<ComunicacionTO> data;

	public List<ComunicacionTO> getData() {
		return data;
	}

	public void setData(List<ComunicacionTO> data) {
		this.data = data;
	}
	
}
