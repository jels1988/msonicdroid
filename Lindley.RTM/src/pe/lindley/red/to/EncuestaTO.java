package pe.lindley.red.to;

import com.google.gson.annotations.SerializedName;

public class EncuestaTO {
	
	@SerializedName("FEC")
	private String fecha;

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
}
