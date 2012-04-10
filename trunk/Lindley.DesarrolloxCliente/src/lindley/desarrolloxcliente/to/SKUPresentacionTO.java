package lindley.desarrolloxcliente.to;

import com.google.gson.annotations.SerializedName;

public class SKUPresentacionTO {
	
	@SerializedName("CSKU")
	private String codigoSKU;
	
	@SerializedName("DSKU")
	private String descripcionSKU;
	
	@SerializedName("SLC")
	public boolean seleccionado;
	

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
}
