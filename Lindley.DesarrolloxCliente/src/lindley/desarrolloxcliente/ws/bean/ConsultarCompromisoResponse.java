package lindley.desarrolloxcliente.ws.bean;

import java.util.List;

import net.msonic.lib.ResponseBase;
import lindley.desarrolloxcliente.to.CompromisoTO;
import com.google.gson.annotations.SerializedName;

public class ConsultarCompromisoResponse extends ResponseBase {

	@SerializedName("CMP")
	private List<CompromisoTO> listaCompromiso;

	public List<CompromisoTO> getListaCompromiso() {
		return listaCompromiso;
	}

	public void setListaCompromiso(List<CompromisoTO> listaCompromiso) {
		this.listaCompromiso = listaCompromiso;
	}
}
