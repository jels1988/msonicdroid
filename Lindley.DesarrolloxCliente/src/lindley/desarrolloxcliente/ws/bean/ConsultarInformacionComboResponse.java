package lindley.desarrolloxcliente.ws.bean;

import lindley.desarrolloxcliente.to.InformacionAdicionalCompromisoTO;

import com.google.gson.annotations.SerializedName;

import net.msonic.lib.ResponseBase;

public class ConsultarInformacionComboResponse extends ResponseBase {

	@SerializedName("INF")
	public InformacionAdicionalCompromisoTO informacion;
}
