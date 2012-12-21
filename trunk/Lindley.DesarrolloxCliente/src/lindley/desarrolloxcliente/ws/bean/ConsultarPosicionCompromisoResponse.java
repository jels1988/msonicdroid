package lindley.desarrolloxcliente.ws.bean;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lindley.desarrolloxcliente.to.PosicionCompromisoTO;

import net.msonic.lib.ResponseBase;

public class ConsultarPosicionCompromisoResponse extends ResponseBase {

	@Expose()
	@SerializedName("POS")
	private List<PosicionCompromisoTO> listaCompromisos;

	public List<PosicionCompromisoTO> getListaCompromisos() {
		return listaCompromisos;
	}

	public void setListaCompromisos(List<PosicionCompromisoTO> listaCompromisos) {
		this.listaCompromisos = listaCompromisos;
	}
}
