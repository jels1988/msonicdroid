package pe.lindley.mmil.ws.bean;

import com.google.gson.annotations.SerializedName;

public class ListarCdaRequest {
	@SerializedName("REG")
	private String codigoRegion;

	public String getCodigoRegion() {
		return codigoRegion;
	}

	public void setCodigoRegion(String codigoRegion) {
		this.codigoRegion = codigoRegion;
	}
	
}
