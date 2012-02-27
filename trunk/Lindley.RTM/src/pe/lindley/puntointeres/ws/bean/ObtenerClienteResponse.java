package pe.lindley.puntointeres.ws.bean;

import pe.lindley.puntointeres.to.ClienteTO;
import pe.lindley.util.ResponseBase;
import com.google.gson.annotations.SerializedName;

public class ObtenerClienteResponse extends ResponseBase{
	
	@SerializedName("CLI")
	private ClienteTO obtenerCliente;

	public ClienteTO getObtenerCliente() {
		return obtenerCliente;
	}

	public void setObtenerCliente(ClienteTO obtenerCliente) {
		this.obtenerCliente = obtenerCliente;
	}

}
