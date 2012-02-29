package lindley.desarrolloxcliente.ws.bean;

import net.msonic.lib.ResponseBase;
import lindley.desarrolloxcliente.to.ClienteTO;
import com.google.gson.annotations.SerializedName;

public class ConsultarClienteResponse extends ResponseBase {
	
	@SerializedName("CLI")
	private ClienteTO clienteTO;

	public ClienteTO getClienteTO() {
		return clienteTO;
	}

	public void setClienteTO(ClienteTO clienteTO) {
		this.clienteTO = clienteTO;
	}
}
