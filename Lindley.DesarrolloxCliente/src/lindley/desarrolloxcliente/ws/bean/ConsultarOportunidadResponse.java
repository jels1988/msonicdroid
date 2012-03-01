package lindley.desarrolloxcliente.ws.bean;

import java.util.List;
import lindley.desarrolloxcliente.to.OportunidadTO;
import com.google.gson.annotations.SerializedName;

import net.msonic.lib.ResponseBase;

public class ConsultarOportunidadResponse extends ResponseBase {

	@SerializedName("OPR")
	private List<OportunidadTO> listaOportunidad;

	public List<OportunidadTO> getListaOportunidad() {
		return listaOportunidad;
	}

	public void setListaOportunidad(List<OportunidadTO> listaOportunidad) {
		this.listaOportunidad = listaOportunidad;
	}
}
 