package pe.lindley.ficha.ws.bean;

import com.google.gson.annotations.SerializedName;
import pe.lindley.ficha.to.EncuestaTO;
import pe.lindley.util.ResponseBase;

public class ObtenerEncuestaResponse extends ResponseBase {

	@SerializedName("ENC")
	private EncuestaTO encuesta;

	public EncuestaTO getEncuesta() {
		return encuesta;
	}

	public void setEncuesta(EncuestaTO encuesta) {
		this.encuesta = encuesta;
	}
}
