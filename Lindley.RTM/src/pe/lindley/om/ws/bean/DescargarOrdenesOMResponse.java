package pe.lindley.om.ws.bean;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import pe.lindley.om.to.OrdenTrabajoTO;
import pe.lindley.util.ResponseBase;

public class DescargarOrdenesOMResponse extends ResponseBase {

	@SerializedName("Ordenes")
	private List<OrdenTrabajoTO> ordenes;

	public List<OrdenTrabajoTO> getOrdenes() {
		return ordenes;
	}

	public void setOrdenes(List<OrdenTrabajoTO> ordenes) {
		this.ordenes = ordenes;
	}
}
