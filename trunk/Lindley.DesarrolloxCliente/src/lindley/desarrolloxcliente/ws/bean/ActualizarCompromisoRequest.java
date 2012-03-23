package lindley.desarrolloxcliente.ws.bean;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import lindley.desarrolloxcliente.to.CompromisoTO;
import lindley.desarrolloxcliente.to.UpdatePosicionTO;
import lindley.desarrolloxcliente.to.UpdatePresentacionTO;

public class ActualizarCompromisoRequest {

	@SerializedName("COM")
	private List<CompromisoTO> compromisos;

	@SerializedName("POS")
	public List<UpdatePosicionTO> listaPosicionCompromiso;

	@SerializedName("PRE")
	public List<UpdatePresentacionTO> listaPresentacionCompromiso;
	        
	public List<CompromisoTO> getCompromisos() {
		return compromisos;
	}

	public void setCompromisos(List<CompromisoTO> compromisos) {
		this.compromisos = compromisos;
	}
}
