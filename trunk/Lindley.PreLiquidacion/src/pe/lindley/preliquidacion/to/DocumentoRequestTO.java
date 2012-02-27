package pe.lindley.preliquidacion.to;

import com.google.gson.annotations.SerializedName;

public class DocumentoRequestTO {

	@SerializedName("DEP")
	private String deposito;
	
	@SerializedName("USU")
	private String usuario;
	
	@SerializedName("TDO")
	private String tipo;
	
	@SerializedName("DOC")
	private String nroDocumento;
	
	@SerializedName("EST")
	private String estado;

	@SerializedName("MOT")
	private String motivo;
		
	@SerializedName("LAT")
	private String latitud;
	
	@SerializedName("LON")
	private String longitud;

	public String getDeposito() {
		return deposito;
	}

	public void setDeposito(String deposito) {
		this.deposito = deposito;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNroDocumento() {
		return nroDocumento;
	}

	public void setNroDocumento(String nroDocumento) {
		this.nroDocumento = nroDocumento;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public String getLatitud() {
		return latitud;
	}

	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}

	public String getLongitud() {
		return longitud;
	}

	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}
	
}
