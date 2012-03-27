package lindley.desarrolloxcliente.ws.bean;

import java.util.List;

import lindley.desarrolloxcliente.to.CompromisoTO;
import lindley.desarrolloxcliente.to.UpdatePosicionTO;
import lindley.desarrolloxcliente.to.UpdatePresentacionTO;

import com.google.gson.annotations.SerializedName;

public class CerrarCompromisoRequest {

	@SerializedName("COD")
	private String codigoCabecera;

	public String getCodigoCabecera() {
		return codigoCabecera;
	}

	public void setCodigoCabecera(String codigoCabecera) {
		this.codigoCabecera = codigoCabecera;
	}
	
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
