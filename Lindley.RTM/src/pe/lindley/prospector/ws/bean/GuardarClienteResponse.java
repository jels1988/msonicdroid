package pe.lindley.prospector.ws.bean;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import pe.lindley.prospector.to.ClienteIdTO;
import pe.lindley.util.ResponseBase;

public class GuardarClienteResponse extends ResponseBase {

	
	@SerializedName("IdGenerados")
	private List<ClienteIdTO> idGenerados;

	public List<ClienteIdTO> getIdGenerados() {
		return idGenerados;
	}

	public void setIdGenerados(List<ClienteIdTO> idGenerados) {
		this.idGenerados = idGenerados;
	}
}
