package pe.lindley.om.ws.bean;

import com.google.gson.annotations.SerializedName;

public class DescargarClientesRequest {
	
	@SerializedName("CodigoDeposito")
	private String codigoDeposito;
	
	@SerializedName("CodigoRuta")
	private String codigoRuta;
	
	public String getCodigoDeposito() {
		return codigoDeposito;
	}
	public void setCodigoDeposito(String codigoDeposito) {
		this.codigoDeposito = codigoDeposito;
	}
	public String getCodigoRuta() {
		return codigoRuta;
	}
	public void setCodigoRuta(String codigoRuta) {
		this.codigoRuta = codigoRuta;
	}
}
