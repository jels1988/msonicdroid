package net.msonic.gps.ws.bean;

import com.google.gson.annotations.SerializedName;

public class GpsPosRequest {

	@SerializedName("TLF")
	private String telefono;
	
	@SerializedName("LTD")
	private String latitud;
	
	@SerializedName("LNG")
	private String longitud;

	@SerializedName("FIX")
	private String fixes;
	
	@SerializedName("ALT")
	private String altitud;
	
	@SerializedName("ACC")
	private String accuracy;
	
	@SerializedName("TIM")
	private String timeStamp;
	
	
	public String getFixes() {
		return fixes;
	}

	public void setFixes(String fixes) {
		this.fixes = fixes;
	}

	public String getAltitud() {
		return altitud;
	}

	public void setAltitud(String altitud) {
		this.altitud = altitud;
	}

	public String getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(String accuracy) {
		this.accuracy = accuracy;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
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
