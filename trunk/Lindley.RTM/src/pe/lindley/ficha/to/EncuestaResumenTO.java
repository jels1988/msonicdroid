package pe.lindley.ficha.to;

import com.google.gson.annotations.SerializedName;

public class EncuestaResumenTO {
	@SerializedName("CLI")
	private String codigoCliente;
	
	@SerializedName("COD")
	private String codigoEncuesta;
	
	public String getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public String getCodigoEncuesta() {
		return codigoEncuesta;
	}

	public void setCodigoEncuesta(String codigoEncuesta) {
		this.codigoEncuesta = codigoEncuesta;
	}

	public String getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getFechaUpdate() {
		return fechaUpdate;
	}

	public void setFechaUpdate(String fechaUpdate) {
		this.fechaUpdate = fechaUpdate;
	}

	@SerializedName("FCR")
	private String fechaCreacion;
	
	@SerializedName("FUP")
	private String fechaUpdate;
	
	@SerializedName("EST")
	private String estado;

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
}
