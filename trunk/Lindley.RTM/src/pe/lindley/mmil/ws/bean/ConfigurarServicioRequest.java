package pe.lindley.mmil.ws.bean;

import com.google.gson.annotations.SerializedName;

public class ConfigurarServicioRequest {

	@SerializedName("MOD")
	private String modulo;

	public String getModulo() {
		return modulo;
	}

	public void setModulo(String modulo) {
		this.modulo = modulo;
	}
	
	
}
