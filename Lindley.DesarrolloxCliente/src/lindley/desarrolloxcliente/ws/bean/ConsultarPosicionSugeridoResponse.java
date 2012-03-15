package lindley.desarrolloxcliente.ws.bean;

import java.util.List;

import lindley.desarrolloxcliente.to.PosicionTO;

import com.google.gson.annotations.SerializedName;

import net.msonic.lib.ResponseBase;

public class ConsultarPosicionSugeridoResponse extends ResponseBase {

	@SerializedName("POS")
	private List<PosicionTO> listaPosicion;

	public List<PosicionTO> getListaPosicion() {
		return listaPosicion;
	}

	public void setListaPosicion(List<PosicionTO> listaPosicion) {
		this.listaPosicion = listaPosicion;
	}
}
