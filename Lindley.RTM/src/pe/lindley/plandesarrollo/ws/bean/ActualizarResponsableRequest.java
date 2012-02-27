package pe.lindley.plandesarrollo.ws.bean;

import com.google.gson.annotations.SerializedName;

public class ActualizarResponsableRequest {
	@SerializedName("CLI")
	private String codigoCliente;

	@SerializedName("CPL")
	private String codigoPLan;

	@SerializedName("CACT")
	private String codigoActvidad;

	@SerializedName("RSP")
	private String codigoResponsableAntiguo;
	
	@SerializedName("RSP2")
	private String codigoResponsableNuevo;
	
	@SerializedName("URG")
	private String usuario;

	public String getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public String getCodigoPLan() {
		return codigoPLan;
	}

	public void setCodigoPLan(String codigoPLan) {
		this.codigoPLan = codigoPLan;
	}

	public String getCodigoActvidad() {
		return codigoActvidad;
	}

	public void setCodigoActvidad(String codigoActvidad) {
		this.codigoActvidad = codigoActvidad;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getCodigoResponsableAntiguo() {
		return codigoResponsableAntiguo;
	}

	public void setCodigoResponsableAntiguo(String codigoResponsableAntiguo) {
		this.codigoResponsableAntiguo = codigoResponsableAntiguo;
	}

	public String getCodigoResponsableNuevo() {
		return codigoResponsableNuevo;
	}

	public void setCodigoResponsableNuevo(String codigoResponsableNuevo) {
		this.codigoResponsableNuevo = codigoResponsableNuevo;
	}
}
