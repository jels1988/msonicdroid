package pe.lindley.equipofrio.ws.bean;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import pe.lindley.equipofrio.to.EquipoFrioTO;
import pe.lindley.util.ResponseBase;

public class ConsultarEquipoFrioResponse extends ResponseBase {

	@SerializedName("EquiposFrio")
	private List<EquipoFrioTO> equipos;

	public List<EquipoFrioTO> getEquipos() {
		return equipos;
	}

	public void setEquipos(List<EquipoFrioTO> equipos) {
		this.equipos = equipos;
	}
}
