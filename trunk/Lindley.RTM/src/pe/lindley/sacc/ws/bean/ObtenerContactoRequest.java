package pe.lindley.sacc.ws.bean;

import com.google.gson.annotations.SerializedName;

public class ObtenerContactoRequest {

	@SerializedName("CLI")
	private int idCliente;

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	
}
