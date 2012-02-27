package pe.lindley.preliquidacion.ws.bean;

import com.google.gson.annotations.SerializedName;

public class ListarDocumentoRequest {

	@SerializedName("DEP")
	private String desposito;
	
	@SerializedName("OCA")
	private String numeroCarga;

	public String getDesposito() {
		return desposito;
	}

	public void setDesposito(String desposito) {
		this.desposito = desposito;
	}

	public String getNumeroCarga() {
		return numeroCarga;
	}

	public void setNumeroCarga(String numeroCarga) {
		this.numeroCarga = numeroCarga;
	}
	
	
	
}
