package lindley.desarrolloxcliente.ws.bean;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import lindley.desarrolloxcliente.to.UpdateInformacionAdicionalTO;
import lindley.desarrolloxcliente.to.UpdateInventarioTO;
import lindley.desarrolloxcliente.to.UpdatePosicionTO;
import lindley.desarrolloxcliente.to.UpdatePresentacionTO;

public class ActualizarCompromisoRequest {

	@SerializedName("COM")
	public List<UpdateInventarioTO> listaInventarioCompromiso;

	@SerializedName("POS")
	public List<UpdatePosicionTO> listaPosicionCompromiso;

	@SerializedName("PRE")
	public List<UpdatePresentacionTO> listaPresentacionCompromiso;
	
	@SerializedName("INF")
	public UpdateInformacionAdicionalTO updateInformacionAdicionalTO;
	
	@SerializedName("COD")
	public String codigoCabecera;
	        
}
