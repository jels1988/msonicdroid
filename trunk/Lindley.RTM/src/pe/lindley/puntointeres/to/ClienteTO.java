package pe.lindley.puntointeres.to;

import com.google.gson.annotations.SerializedName;

public class ClienteTO {
	
	@SerializedName("COD")
	private int codigo;

	@SerializedName("LAT")
	private String latitud;
	        
	@SerializedName("LNG")
	private String longitud;
	        
	@SerializedName("LTD")
	private double latitudDec;
	        
	@SerializedName("LGD")
	private double longitudDec;
	        
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getLatitud() {
		return latitud;
	}

	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}

	public String getLongitud() {
		return longitud;
	}

	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}

	public double getLatitudDec() {
		return latitudDec;
	}

	public void setLatitudDec(double latitudDec) {
		this.latitudDec = latitudDec;
	}

	public double getLongitudDec() {
		return longitudDec;
	}

	public void setLongitudDec(double longitudDec) {
		this.longitudDec = longitudDec;
	}

}
