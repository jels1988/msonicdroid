package lindley.desarrolloxcliente.to;

import com.google.gson.annotations.SerializedName;

public class SKUPresentacionCompromisoTO {

	@SerializedName("CSKU")
	private String codigoSKU;
	
	@SerializedName("DSKU")
	private String descripcionSKU;

	@SerializedName("ACT")
	private String actual;

	@SerializedName("CMP")
	private String compromiso;

	@SerializedName("CNF")
	private String confirmacion;

	public String getCodigoSKU() {
		return codigoSKU;
	}

	public void setCodigoSKU(String codigoSKU) {
		this.codigoSKU = codigoSKU;
	}

	public String getDescripcionSKU() {
		return descripcionSKU;
	}

	public void setDescripcionSKU(String descripcionSKU) {
		this.descripcionSKU = descripcionSKU;
	}

	public String getActual() {
		return actual;
	}

	public void setActual(String actual) {
		this.actual = actual;
	}

	public String getCompromiso() {
		return compromiso;
	}

	public void setCompromiso(String compromiso) {
		this.compromiso = compromiso;
	}

	public String getConfirmacion() {
		return confirmacion;
	}

	public void setConfirmacion(String confirmacion) {
		this.confirmacion = confirmacion;
	}

}
