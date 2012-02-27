package pe.lindley.plandesarrollo.ws.bean;

import com.google.gson.annotations.SerializedName;

public class GuardarActividadRequest {

	@SerializedName("CLI")
	private String codigoCliente;
	
	@SerializedName("CPL")
	private String codigoPLan;
	
	@SerializedName("DES")
	private String descripcionActividad;
	
	@SerializedName("FTV")
	private String fechTentativa;

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

	public String getDescripcionActividad() {
		return descripcionActividad;
	}

	public void setDescripcionActividad(String descripcionActividad) {
		this.descripcionActividad = descripcionActividad;
	}

	public String getFechTentativa() {
		return fechTentativa;
	}

	public void setFechTentativa(String fechTentativa) {
		this.fechTentativa = fechTentativa;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

}
