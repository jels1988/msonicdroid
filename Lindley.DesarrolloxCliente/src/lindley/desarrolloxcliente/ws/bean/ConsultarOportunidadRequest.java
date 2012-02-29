package lindley.desarrolloxcliente.ws.bean;

import com.google.gson.annotations.SerializedName;

public class ConsultarOportunidadRequest {

	@SerializedName("COD")
	private String codigoCliente;

	public String getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}
}
