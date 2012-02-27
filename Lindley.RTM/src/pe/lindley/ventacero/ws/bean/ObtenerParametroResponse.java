package pe.lindley.ventacero.ws.bean;

import java.util.List;
import com.google.gson.annotations.SerializedName;
import pe.lindley.util.ResponseBase;
import pe.lindley.ventacero.to.ParametroTO;

public class ObtenerParametroResponse extends ResponseBase{

	@SerializedName("PRM")
	private List<ParametroTO> listaParametro;

	public List<ParametroTO> getListaParametro() {
		return listaParametro;
	}

	public void setListaMotivo(List<ParametroTO> listaParametro) {
		this.listaParametro = listaParametro;
	}
}
