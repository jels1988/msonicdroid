package lindley.desarrolloxcliente.ws.bean;

import com.google.gson.annotations.SerializedName;

public class ConsultarPresentacionSugeridoRequest {

	@SerializedName("CLI")
	private String codigoCliente;

	public String getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}
}
