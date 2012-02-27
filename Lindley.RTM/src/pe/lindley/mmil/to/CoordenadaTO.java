package pe.lindley.mmil.to;

import com.google.gson.annotations.SerializedName;

public class CoordenadaTO {
	
	@SerializedName("LAT")
	private double latitud;
	
	@SerializedName("LNG")
	private double longitud;

	public double getLatitud() {
		return latitud;
	}

	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}

	public double getLongitud() {
		return longitud;
	}

	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}


}
