package pe.lindley.prospector.ws.bean;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

import pe.lindley.prospector.to.ClienteTO;

public class GuardarClienteRequest {
	
	@SerializedName("Clientes")
	private ArrayList<ClienteTO> clientes;

	public ArrayList<ClienteTO> getClientes() {
		return clientes;
	}

	public void setClientes(ArrayList<ClienteTO> clientes) {
		this.clientes = clientes;
	}
}
