package pe.lindley.om.ws.bean;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

import pe.lindley.om.to.OrdenTrabajoTO;

public class EnviarOrdenesRequest {
	
	@SerializedName("Ordenes")
	private ArrayList<OrdenTrabajoTO> ordenes;

	public ArrayList<OrdenTrabajoTO> getOrdenes() {
		return ordenes;
	}

	public void setOrdenes(ArrayList<OrdenTrabajoTO> ordenes) {
		this.ordenes = ordenes;
	}
}
