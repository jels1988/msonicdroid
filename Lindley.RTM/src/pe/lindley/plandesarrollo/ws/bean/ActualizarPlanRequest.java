package pe.lindley.plandesarrollo.ws.bean;

import com.google.gson.annotations.SerializedName;

public class ActualizarPlanRequest {
	
	@SerializedName("CLI")
	private String codigoCliente;

	@SerializedName("CPL")
	private String codigoPLan;
	
	@SerializedName("PRJA")
	private String porcentajeAvance;
	
	@SerializedName("DPL")
	private String descripcionPLan;

	@SerializedName("DOB")
	private String descripcionObjetivo;
	
	@SerializedName("FCRE")
	private String fechaCreacion;
	
	@SerializedName("FINI")
	private String fechaInicio;
	
	@SerializedName("FFIN")
	private String fechaFin;
	
	@SerializedName("EST")
	private String estado;
	
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

	public String getPorcentajeAvance() {
		return porcentajeAvance;
	}

	public void setPorcentajeAvance(String porcentajeAvance) {
		this.porcentajeAvance = porcentajeAvance;
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

	public String getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
}
