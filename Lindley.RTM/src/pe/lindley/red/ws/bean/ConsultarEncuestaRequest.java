package pe.lindley.red.ws.bean;

import com.google.gson.annotations.SerializedName;

public class ConsultarEncuestaRequest {

	@SerializedName("CCL")
	private String codigoCliente;

	public String getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}
	
}
