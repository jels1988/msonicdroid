package pe.lindley.ficha.ws.bean;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import pe.lindley.ficha.to.EncuestaResumenTO;
import pe.lindley.util.ResponseBase;

public class ConsultarEncuestaResponse extends ResponseBase {
	
	@SerializedName("ENC")
	private List<EncuestaResumenTO> encuestas;

	public List<EncuestaResumenTO> getEncuestas() {
		return encuestas;
	}

	public void setEncuestas(List<EncuestaResumenTO> encuestas) {
		this.encuestas = encuestas;
	}
	
}
