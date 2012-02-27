package pe.lindley.profit.ws.bean;

import java.util.List;
import pe.lindley.profit.to.ParametroTO;
import pe.lindley.util.ResponseBase;

import com.google.gson.annotations.SerializedName;

public class DescargarParametrosResponse extends ResponseBase {
	
	@SerializedName("PRM")
	private List<ParametroTO>  listaParametro;

	public List<ParametroTO> getListaParametro() {
		return listaParametro;
	}

	public void setListaParametro(List<ParametroTO> listaParametro) {
		this.listaParametro = listaParametro;
	}

}
