package pe.lindley.puntointeres.ws.bean;

import com.google.gson.annotations.SerializedName;

public class EliminarPuntoInteresRequest {

	@SerializedName("COD")
	private String codCliente;

	@SerializedName("PIT")
	private String codPunto;
	
	@SerializedName("URG")
	private String Usuario;

	public String getCodCliente() {
		return codCliente;
	}

	public void setCodCliente(String codCliente) {
		this.codCliente = codCliente;
	}

	public String getCodPunto() {
		return codPunto;
	}

	public void setCodPunto(String codPunto) {
		this.codPunto = codPunto;
	}

	public String getUsuario() {
		return Usuario;
	}

	public void setUsuario(String usuario) {
		Usuario = usuario;
	}
}
