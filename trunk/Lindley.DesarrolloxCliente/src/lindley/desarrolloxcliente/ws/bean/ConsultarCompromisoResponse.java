package lindley.desarrolloxcliente.ws.bean;

import java.util.List;
import lindley.desarrolloxcliente.to.CompromisoTO;
import com.google.gson.annotations.SerializedName;

public class ConsultarCompromisoResponse {

	@SerializedName("CMP")
	private List<CompromisoTO> listaCompromiso;

	public List<CompromisoTO> getListaCompromiso() {
		return listaCompromiso;
	}

	public void setListaCompromiso(List<CompromisoTO> listaCompromiso) {
		this.listaCompromiso = listaCompromiso;
	}
}
