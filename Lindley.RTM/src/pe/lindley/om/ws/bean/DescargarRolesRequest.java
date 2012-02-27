package pe.lindley.om.ws.bean;

import com.google.gson.annotations.SerializedName;

public class DescargarRolesRequest {
	
	
	@SerializedName("TipoRol")
	private String tipoRol;
	
	@SerializedName("CodigoDeposito")
	private String codigoDeposito;
	
	public String getTipoRol() {
		return tipoRol;
	}
	public void setTipoRol(String tipoRol) {
		this.tipoRol = tipoRol;
	}
	public String getCodigoDeposito() {
		return codigoDeposito;
	}
	public void setCodigoDeposito(String codigoDeposito) {
		this.codigoDeposito = codigoDeposito;
	}
	
}
