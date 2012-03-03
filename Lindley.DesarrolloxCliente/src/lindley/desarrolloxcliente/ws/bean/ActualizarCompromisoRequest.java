package lindley.desarrolloxcliente.ws.bean;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import lindley.desarrolloxcliente.to.CompromisoTO;

public class ActualizarCompromisoRequest {

	@SerializedName("COM")
	private List<CompromisoTO> compromisos;

	public List<CompromisoTO> getCompromisos() {
		return compromisos;
	}

	public void setCompromisos(List<CompromisoTO> compromisos) {
		this.compromisos = compromisos;
	}
}
