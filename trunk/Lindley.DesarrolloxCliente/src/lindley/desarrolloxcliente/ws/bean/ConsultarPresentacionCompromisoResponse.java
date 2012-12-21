package lindley.desarrolloxcliente.ws.bean;

import java.util.List;

import lindley.desarrolloxcliente.to.PresentacionCompromisoTO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import net.msonic.lib.ResponseBase;

public class ConsultarPresentacionCompromisoResponse extends ResponseBase {

	@Expose()
	@SerializedName("PRE")
	private List<PresentacionCompromisoTO> listaCompromisos;

	public List<PresentacionCompromisoTO> getListaCompromisos() {
		return listaCompromisos;
	}

	public void setListaCompromisos(List<PresentacionCompromisoTO> listaCompromisos) {
		this.listaCompromisos = listaCompromisos;
	}
	
}
