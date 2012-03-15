package lindley.desarrolloxcliente.ws.bean;

import com.google.gson.annotations.SerializedName;

public class ConsultarPosicionSugeridoRequest {

	@SerializedName("CLI")
	private String codigoCliente;

	@SerializedName("RSP")
	private String respuesta;

	public String getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public String getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}
}
