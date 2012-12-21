package lindley.desarrolloxcliente.ws.bean;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lindley.desarrolloxcliente.to.download.ClienteDescargaTO;

import net.msonic.lib.ResponseBase;

public class DescargarClienteResponse extends ResponseBase {
	
	@Expose()
	@SerializedName("CLI")
	public List<ClienteDescargaTO> clientes;
}
