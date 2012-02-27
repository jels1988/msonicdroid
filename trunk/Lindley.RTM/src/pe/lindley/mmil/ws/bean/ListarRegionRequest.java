package pe.lindley.mmil.ws.bean;

import com.google.gson.annotations.SerializedName;

public class ListarRegionRequest {
	
	@SerializedName("Pais")
	private String pais;

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

}
