package pe.lindley.ficha.ws.bean;

import com.google.gson.annotations.SerializedName;

public class ActualizarComercialRequest {
	
	@SerializedName("CLI")
	private String codigo;

	@SerializedName("GIR")
	private String giroSecundario;
	
	@SerializedName("URG")
	private String usuario;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String cliente) {
		this.codigo = cliente;
	}

	public String getGiroSecundario() {
		return giroSecundario;
	}

	public void setGiroSecundario(String giroSecundario) {
		this.giroSecundario = giroSecundario;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

}
