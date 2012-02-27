package pe.lindley.om.ws.bean;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import pe.lindley.om.to.EventoTO;
import pe.lindley.util.ResponseBase;

public class DescargarEventosOMResponse extends ResponseBase {
	
	@SerializedName("Eventos")
	private List<EventoTO> eventos;

	public List<EventoTO> getEventos() {
		return eventos;
	}

	public void setEventos(List<EventoTO> eventos) {
		this.eventos = eventos;
	}

}
