package pe.lindley.om.ws.bean;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import pe.lindley.om.to.ParametroTO;
import pe.lindley.util.ResponseBase;

public class DescargarParametrosResponse extends ResponseBase {
	
	@SerializedName("Parametros")
	private List<ParametroTO> parametros;

	public List<ParametroTO> getParametros() {
		return parametros;
	}

	public void setParametros(List<ParametroTO> parametros) {
		this.parametros = parametros;
	}
	
}
