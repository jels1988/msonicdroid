package pe.lindley.om.ws.bean;

import com.google.gson.annotations.SerializedName;

public class DescargarEventosOMRequest {
	@SerializedName("CodigoSap")
	private String codigoSap;

	public String getCodigoSap() {
		return codigoSap;
	}

	public void setCodigoSap(String codigoSap) {
		this.codigoSap = codigoSap;
	}
}
