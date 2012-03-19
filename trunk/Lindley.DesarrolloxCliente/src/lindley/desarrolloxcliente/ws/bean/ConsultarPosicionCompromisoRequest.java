package lindley.desarrolloxcliente.ws.bean;

import com.google.gson.annotations.SerializedName;

public class ConsultarPosicionCompromisoRequest {

	@SerializedName("CLI")
	private String codigoCliente;

	@SerializedName("CODR")
	private String codigoGestion;

	//@SerializedName("RSP")
	//private String respuesta;

	public String getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public String getCodigoGestion() {
		return codigoGestion;
	}

	public void setCodigoGestion(String codigoGestion) {
		this.codigoGestion = codigoGestion;
	}

	/*
	public String getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}
	*/
}
