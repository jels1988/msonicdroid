package lindley.desarrolloxcliente.ws.bean;

import java.util.List;
import lindley.desarrolloxcliente.to.DesarrolloClienteTO;
import com.google.gson.annotations.SerializedName;

import net.msonic.lib.ResponseBase;

public class ConsultarCabeceraResponse extends ResponseBase {

	@SerializedName("CAB")
	private List<DesarrolloClienteTO> listaDesarrolloCliente;

	public List<DesarrolloClienteTO> getListaDesarrolloCliente() {
		return listaDesarrolloCliente;
	}
 
	public void setListaDesarrolloCliente(
			List<DesarrolloClienteTO> listaDesarrolloCliente) {
		this.listaDesarrolloCliente = listaDesarrolloCliente;
	}
}
