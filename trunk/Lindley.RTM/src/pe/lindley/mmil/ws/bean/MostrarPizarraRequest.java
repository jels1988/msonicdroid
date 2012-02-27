package pe.lindley.mmil.ws.bean;

import com.google.gson.annotations.SerializedName;

public class MostrarPizarraRequest {
	
	@SerializedName("COD")
	private String codigoDeposito;

	public String getCodigoDeposito() {
		return codigoDeposito;
	}

	public void setCodigoDeposito(String codigoDeposito) {
		this.codigoDeposito = codigoDeposito;
	}

}