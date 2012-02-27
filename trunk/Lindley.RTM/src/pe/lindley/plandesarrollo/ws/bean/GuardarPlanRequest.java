package pe.lindley.plandesarrollo.ws.bean;

import com.google.gson.annotations.SerializedName;

public class GuardarPlanRequest {
	
	@SerializedName("CLI")
	private String codigoCliente;
	
	@SerializedName("DPL")
	private String descripcionPLan;

	@SerializedName("DOB")
	private String descripcionObjetivo;
	
	@SerializedName("FCRE")
	private String fechaCreacion;
	
	@SerializedName("FINI")
	private String fechaInicio;
	
	@SerializedName("URG")
	private String usuario;

	public String getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}
	
	public String getDescripcionPLan() {
		return descripcionPLan;
	}

	public void setDescripcionPLan(String descripcionPLan) {
		this.descripcionPLan = descripcionPLan;
	}

	public String getDescripcionObjetivo() {
		return descripcionObjetivo;
	}

	public void setDescripcionObjetivo(String descripcionObjetivo) {
		this.descripcionObjetivo = descripcionObjetivo;
	}

	public String getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
}
