package pe.lindley.puntointeres.ws.bean;

import java.util.List;
import com.google.gson.annotations.SerializedName;
import pe.lindley.puntointeres.to.ParametroTO;
import pe.lindley.util.ResponseBase;

public class ObtenerParametroResponse extends ResponseBase {

	@SerializedName("PRM")
	private List<ParametroTO> listaParametro;

	public List<ParametroTO> getListaParametro() {
		return listaParametro;
	}

	public void setListaParametro(List<ParametroTO> listaParametro) {
		this.listaParametro = listaParametro;
	}
}
