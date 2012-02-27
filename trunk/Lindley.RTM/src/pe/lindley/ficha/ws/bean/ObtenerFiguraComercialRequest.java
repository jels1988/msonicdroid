package pe.lindley.ficha.ws.bean;

import com.google.gson.annotations.SerializedName;

public class ObtenerFiguraComercialRequest {

	@SerializedName("CLI")
	private String codigo;

	public String getCliente() {
		return codigo;
	}

	public void setCliente(String cliente) {
		codigo = cliente;
	}
}
