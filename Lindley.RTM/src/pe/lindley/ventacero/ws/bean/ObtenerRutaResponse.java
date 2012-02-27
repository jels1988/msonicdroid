package pe.lindley.ventacero.ws.bean;

import java.util.List;
import com.google.gson.annotations.SerializedName;
import pe.lindley.util.ResponseBase;
import pe.lindley.ventacero.to.RutaTO;

public class ObtenerRutaResponse extends ResponseBase{
	
	@SerializedName("RTS")
	private List<RutaTO> ListaRuta;

	public List<RutaTO> getListaRuta() {
		return ListaRuta;
	}

	public void setListaRuta(List<RutaTO> listaRuta) {
		ListaRuta = listaRuta;
	}

}
