package pe.lindley.ventacero.ws.bean;

import com.google.gson.annotations.SerializedName;

public class ObtenerPeriodoRequest {
	
	@SerializedName("COD")
	private String coddis;

	public String getCoddis() {
		return coddis;
	}

	public void setCoddis(String coddis) {
		this.coddis = coddis;
	}
}