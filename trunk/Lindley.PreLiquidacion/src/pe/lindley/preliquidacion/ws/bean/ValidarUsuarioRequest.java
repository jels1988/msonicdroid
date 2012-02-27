package pe.lindley.preliquidacion.ws.bean;

import com.google.gson.annotations.SerializedName;

public class ValidarUsuarioRequest {
	
	@SerializedName("USU")
	private String usuario;
	
	@SerializedName("DEP")
	private String deposito;
	
	@SerializedName("OCA")
	private String ordenCarga;

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getDeposito() {
		return deposito;
	}

	public void setDeposito(String deposito) {
		this.deposito = deposito;
	}

	public String getOrdenCarga() {
		return ordenCarga;
	}

	public void setOrdenCarga(String ordenCarga) {
		this.ordenCarga = ordenCarga;
	}
	

}
