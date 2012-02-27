package pe.lindley.mmil.to;

import com.google.gson.annotations.SerializedName;

public class PoligonoMapTO {
	
	@SerializedName("PAI")	
	private String pais;

	@SerializedName("REG")
	private String region;

	@SerializedName("DEP")
	private String deposito;

	@SerializedName("LAT")
	private String latitud;

	@SerializedName("LNG")
	private String longitud;

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getDeposito() {
		return deposito;
	}

	public void setDeposito(String deposito) {
		this.deposito = deposito;
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

}
