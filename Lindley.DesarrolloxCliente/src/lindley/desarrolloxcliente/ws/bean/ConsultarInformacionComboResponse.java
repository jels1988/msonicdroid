package lindley.desarrolloxcliente.ws.bean;

import lindley.desarrolloxcliente.to.InformacionAdicionalCompromisoTO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import net.msonic.lib.ResponseBase;

public class ConsultarInformacionComboResponse extends ResponseBase {

	@Expose()
	@SerializedName("INF")
	public InformacionAdicionalCompromisoTO informacion;
}
