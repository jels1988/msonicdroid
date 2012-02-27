package pe.lindley.ficha.ws.bean;

import pe.lindley.ficha.to.ClienteTO;
import pe.lindley.util.ResponseBase;
import com.google.gson.annotations.SerializedName;

public class ObtenerClienteResponse extends ResponseBase{
	
	@SerializedName("CLI")
	private ClienteTO cliente;

	public ClienteTO getCliente() {
		return cliente;
	}

	public void setCliente(ClienteTO cliente) {
		this.cliente = cliente;
	}

}
