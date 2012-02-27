package pe.lindley.ventacero.to;

import com.google.gson.annotations.SerializedName;

public class RutaTO {
	
	@SerializedName("RUT")
	private String codruta;

	public String getCodruta() {
		return codruta;
	}

	public void setCodruta(String codruta) {
		this.codruta = codruta;
	}

}
