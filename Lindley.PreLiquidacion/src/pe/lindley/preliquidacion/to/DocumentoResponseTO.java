package pe.lindley.preliquidacion.to;

import com.google.gson.annotations.SerializedName;

public class DocumentoResponseTO {

	@SerializedName("NDO")
	private String nroDocumento;
	
	@SerializedName("EST")
	private String estado;

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
	
}
