package pe.lindley.ficha.ws.bean;

import com.google.gson.annotations.SerializedName;

public class ActualizarClienteRequest {
	
	@SerializedName("CLI")
	private String cliente;

	@SerializedName("RZC")
	private String razonComercial;

	@SerializedName("REF")
	private String referencia;

	@SerializedName("FEC")
	private String fechaAniversario;

	@SerializedName("OBS")
	private String observaciones;

	@SerializedName("URG")
	private String usuario;

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getRazonComercial() {
		return razonComercial;
	}

	public void setRazonComercial(String razonComercial) {
		this.razonComercial = razonComercial;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	
	public String getFechaAniversario() {
		return fechaAniversario;
	}

	public void setFechaAniversario(String fechaAniversario) {
		this.fechaAniversario = fechaAniversario;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
}