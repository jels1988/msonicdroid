package pe.lindley.plandesarrollo.ws.bean;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import pe.lindley.plandesarrollo.to.ActividadTO;
import pe.lindley.util.ResponseBase;

public class ConsultarActividadResponse extends ResponseBase {

	@SerializedName("ACT")
	private List<ActividadTO> actividades;

	public List<ActividadTO> getActividades() {
		return actividades;
	}

	public void setActividades(List<ActividadTO> actividades) {
		this.actividades = actividades;
	}
}
