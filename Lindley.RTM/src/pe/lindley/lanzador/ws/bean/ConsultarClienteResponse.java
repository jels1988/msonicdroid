package pe.lindley.lanzador.ws.bean;

import java.util.List;

import pe.lindley.lanzador.to.ClienteResumenTO;
import pe.lindley.util.ResponseBase;

import com.google.gson.annotations.SerializedName;

public class ConsultarClienteResponse extends ResponseBase {
	
	public List<ClienteResumenTO> getClientes() {
		return clientes;
	}

	public void setClientes(List<ClienteResumenTO> clientes) {
		this.clientes = clientes;
	}

	@SerializedName("Clientes")
	private List<ClienteResumenTO> clientes;
}
