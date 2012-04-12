package lindley.desarrolloxcliente.ws.bean;

import java.util.List;

import lindley.desarrolloxcliente.to.CerrarInventarioTO;
import lindley.desarrolloxcliente.to.CerrarPosicionTO;
import lindley.desarrolloxcliente.to.CerrarPresentacionTO;

import com.google.gson.annotations.SerializedName;

public class CerrarCompromisoRequest {

	@SerializedName("USCIE")
	public String codigoUsuario;
	
	@SerializedName("COD")
	public String codigoCabecera;
	
	@SerializedName("COM")
	public List<CerrarInventarioTO> listaInventarioCompromiso;

	@SerializedName("POS")
	public List<CerrarPosicionTO> listaPosicionCompromiso;

	@SerializedName("PRE")
	public List<CerrarPresentacionTO> listaPresentacionCompromiso;
}
