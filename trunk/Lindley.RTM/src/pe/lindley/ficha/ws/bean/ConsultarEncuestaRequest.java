package pe.lindley.ficha.ws.bean;

import com.google.gson.annotations.SerializedName;

public class ConsultarEncuestaRequest {

	@SerializedName("CLI")
	private String cliente;

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

}
