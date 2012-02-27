package pe.lindley.sacc.ws.bean;

import java.util.List;

import pe.lindley.sacc.to.EventoTO;
import pe.lindley.util.ResponseBase;

import com.google.gson.annotations.SerializedName;

public class ObtenerEventoResponse extends ResponseBase{
	
	@SerializedName("LDT")
	private List<EventoTO> listaEvento;

	public List<EventoTO> getListaEvento() {
		return listaEvento;
	}

	public void setListaEvento(List<EventoTO> listaEvento) {
		this.listaEvento = listaEvento;
	}

}
