package pe.lindley.red.ws.bean;

import com.google.gson.annotations.SerializedName;

public class ConsultarPreguntaRequest {

	@SerializedName("CCL")
	private String codigoCliente;

	@SerializedName("AM")
	private String anioMes;

	public String getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public String getAnioMes() {
		return anioMes;
	}

	public void setAnioMes(String anioMes) {
		this.anioMes = anioMes;
	}
	
}
