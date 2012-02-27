package pe.lindley.prospector.ws.bean;

import com.google.gson.annotations.SerializedName;

import pe.lindley.prospector.to.ClienteTO;
import pe.lindley.util.ResponseBase;

public class DatosClienteResponse extends ResponseBase {
	
	@SerializedName("Cliente")
	private ClienteTO cliente;

	public ClienteTO getCliente() {
		return cliente;
	}

	public void setCliente(ClienteTO cliente) {
		this.cliente = cliente;
	}
}
