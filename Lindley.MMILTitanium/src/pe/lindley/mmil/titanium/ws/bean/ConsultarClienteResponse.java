package pe.lindley.mmil.titanium.ws.bean;

import com.google.gson.annotations.SerializedName;

import pe.lindley.mmil.titanium.to.ClienteTO;
import net.msonic.lib.ResponseBase;

public class ConsultarClienteResponse extends ResponseBase {
	
	@SerializedName("CLI")
	public ClienteTO cliente;
	
}
