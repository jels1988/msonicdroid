package pe.lindley.puntointeres.ws.bean;

import java.util.List;
import com.google.gson.annotations.SerializedName;
import pe.lindley.puntointeres.to.PuntoInteresTO;
import pe.lindley.util.ResponseBase;

public class ObtenerPuntoInteresResponse extends ResponseBase{

	@SerializedName("CLI")
	private List<PuntoInteresTO> listarPtosInteres;

	public List<PuntoInteresTO> getListarPtosInteres() {
		return listarPtosInteres;
	}

	public void setListarPtosInteres(List<PuntoInteresTO> listarPtosInteres) {
		this.listarPtosInteres = listarPtosInteres;
	}
	
}
