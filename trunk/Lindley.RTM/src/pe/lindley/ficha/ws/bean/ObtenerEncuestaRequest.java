package pe.lindley.ficha.ws.bean;

import com.google.gson.annotations.SerializedName;

public class ObtenerEncuestaRequest {
	
	@SerializedName("OPC")
	private String opcion;

	@SerializedName("ENC")
	private String codigoEncuesta;

	public String getOpcion() {
		return opcion;
	}

	public void setOpcion(String opcion) {
		this.opcion = opcion;
	}

	public String getCodigoEncuesta() {
		return codigoEncuesta;
	}

	public void setCodigoEncuesta(String codigoEncuesta) {
		this.codigoEncuesta = codigoEncuesta;
	}


}
