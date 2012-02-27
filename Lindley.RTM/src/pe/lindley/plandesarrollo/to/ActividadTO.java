package pe.lindley.plandesarrollo.to;

import com.google.gson.annotations.SerializedName;

public class ActividadTO {
		    
	@SerializedName("COD")
	private String codigoActividad;
	    
	@SerializedName("DES")
	private String descripcionActividad;

	@SerializedName("FTV")
	private String fechaTentativa;

	@SerializedName("FEJ")
	private String fechaEjecucion;
	
	@SerializedName("FEC")
	private String fechaCreacion;

	@SerializedName("EST")
	private String estado;

	@SerializedName("RESP")
	private String responsables;

	@SerializedName("SUT")
	private String cantidadSustento;

	public String getCodigoActividad() {
		return codigoActividad;
	}

	public void setCodigoActividad(String codigoActividad) {
		this.codigoActividad = codigoActividad;
	}

	public String getDescripcionActividad() {
		return descripcionActividad;
	}

	public void setDescripcionActividad(String descripcionActividad) {
		this.descripcionActividad = descripcionActividad;
	}

	public String getFechaTentativa() {
		return fechaTentativa;
	}

	public void setFechaTentativa(String fechaTentativa) {
		this.fechaTentativa = fechaTentativa;
	}

	public String getFechaEjecucion() {
		return fechaEjecucion;
	}

	public void setFechaEjecucion(String fechaEjecucion) {
		this.fechaEjecucion = fechaEjecucion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getResponsables() {
		return responsables;
	}

	public void setResponsables(String responsables) {
		this.responsables = responsables;
	}

	public String getCantidadSustento() {
		return cantidadSustento;
	}

	public void setCantidadSustento(String cantidadSustento) {
		this.cantidadSustento = cantidadSustento;
	}

	public String getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

}
