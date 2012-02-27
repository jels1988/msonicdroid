package pe.lindley.plandesarrollo.ws.bean;

import com.google.gson.annotations.SerializedName;

public class GuardarSustentoRequest {

	@SerializedName("CLI")
	private String codigoCliente;
	
	@SerializedName("CPL")
	private String codigoPLan;
	
	@SerializedName("CACT")
	private String codigoActvidad;
	
	@SerializedName("FVIS")
	private String fechaVisita;
	
	@SerializedName("TACT")
	private String tipoActividad;
	
	@SerializedName("DACT")
	private String descripcionActividad;
	
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

	public String getFechaVisita() {
		return fechaVisita;
	}

	public void setFechaVisita(String fechaVisita) {
		this.fechaVisita = fechaVisita;
	}

	public String getTipoActividad() {
		return tipoActividad;
	}

	public void setTipoActividad(String tipoActividad) {
		this.tipoActividad = tipoActividad;
	}

	public String getDescripcionActividad() {
		return descripcionActividad;
	}

	public void setDescripcionActividad(String descripcionActividad) {
		this.descripcionActividad = descripcionActividad;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
}
