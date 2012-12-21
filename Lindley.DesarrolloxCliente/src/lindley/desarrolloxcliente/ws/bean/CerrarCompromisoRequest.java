package lindley.desarrolloxcliente.ws.bean;

import java.util.List;

import lindley.desarrolloxcliente.to.CerrarInventarioTO;
import lindley.desarrolloxcliente.to.CerrarPosicionTO;
import lindley.desarrolloxcliente.to.CerrarPresentacionTO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CerrarCompromisoRequest {

	@Expose()
	@SerializedName("USCIE")
	public String codigoUsuario;
	
	@Expose()
	@SerializedName("COD")
	public String codigoCabecera;
	
	@Expose()
	@SerializedName("COM")
	public List<CerrarInventarioTO> listaInventarioCompromiso;

	@Expose()
	@SerializedName("POS")
	public List<CerrarPosicionTO> listaPosicionCompromiso;

	@Expose()
	@SerializedName("PRE")
	public List<CerrarPresentacionTO> listaPresentacionCompromiso;
}
