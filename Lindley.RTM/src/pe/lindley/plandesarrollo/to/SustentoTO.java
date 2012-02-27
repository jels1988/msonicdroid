package pe.lindley.plandesarrollo.to;

import com.google.gson.annotations.SerializedName;

public class SustentoTO {

	@SerializedName("CSUS")
	private String codigoSustento;
	
	@SerializedName("FVIS")
	private String fechaVisita;
	
	@SerializedName("TACT")
	private String tipoActividad;
	
	@SerializedName("NACT")
	private String nombreActividad;
	
	@SerializedName("NCACT")
	private String nombreCortoActividad;
	
	@SerializedName("DACT")
	private String descripcionActividad;
	
	@SerializedName("CURG")
	private String codigoUsuario;
	
	@SerializedName("NURG")
	private String nombreUsuario;
	
	@SerializedName("HRG")
	private String hora;

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

	public String getNombreActividad() {
		return nombreActividad;
	}

	public void setNombreActividad(String nombreActividad) {
		this.nombreActividad = nombreActividad;
	}

	public String getNombreCortoActividad() {
		return nombreCortoActividad;
	}

	public void setNombreCortoActividad(String nombreCortoActividad) {
		this.nombreCortoActividad = nombreCortoActividad;
	}

	public String getDescripcionActividad() {
		return descripcionActividad;
	}

	public void setDescripcionActividad(String descripcionActividad) {
		this.descripcionActividad = descripcionActividad;
	}

	public String getCodigoUsuario() {
		return codigoUsuario;
	}

	public void setCodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getCodigoSustento() {
		return codigoSustento;
	}

	public void setCodigoSustento(String codigoSustento) {
		this.codigoSustento = codigoSustento;
	}
}
