package lindley.desarrolloxcliente.ws.bean;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lindley.desarrolloxcliente.to.UpdateInformacionAdicionalTO;
import lindley.desarrolloxcliente.to.UpdateInventarioTO;
import lindley.desarrolloxcliente.to.UpdatePosicionTO;
import lindley.desarrolloxcliente.to.UpdatePresentacionTO;

public class ActualizarCompromisoRequest {

	@Expose()
	@SerializedName("COM")
	public List<UpdateInventarioTO> listaInventarioCompromiso;

	@Expose()
	@SerializedName("POS")
	public List<UpdatePosicionTO> listaPosicionCompromiso;

	@Expose()
	@SerializedName("PRE")
	public List<UpdatePresentacionTO> listaPresentacionCompromiso;
	
	@Expose()
	@SerializedName("INF")
	public UpdateInformacionAdicionalTO updateInformacionAdicionalTO;
	
	@Expose()
	@SerializedName("COD")
	public String codigoCabecera;
	        
}
