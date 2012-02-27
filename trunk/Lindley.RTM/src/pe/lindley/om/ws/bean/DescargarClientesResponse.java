package pe.lindley.om.ws.bean;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import pe.lindley.om.to.ClienteTO;
import pe.lindley.util.ResponseBase;

public class DescargarClientesResponse extends ResponseBase {
	
	@SerializedName("Clientes")
	private List<ClienteTO> clientes;

	public List<ClienteTO> getClientes() {
		return clientes;
	}

	public void setClientes(List<ClienteTO> clientes) {
		this.clientes = clientes;
	}
	
}
