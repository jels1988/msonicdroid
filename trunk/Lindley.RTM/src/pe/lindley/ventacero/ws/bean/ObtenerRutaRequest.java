package pe.lindley.ventacero.ws.bean;

import com.google.gson.annotations.SerializedName;

public class ObtenerRutaRequest {
	
	@SerializedName("COD")
	private String codProspector;

	public String getCodProspector() {
		return codProspector;
	}

	public void setCodProspector(String codProspector) {
		this.codProspector = codProspector;
	}

}
