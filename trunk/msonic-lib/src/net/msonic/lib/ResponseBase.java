package net.msonic.lib;

import com.google.gson.annotations.SerializedName;

public abstract class ResponseBase {

	@SerializedName("STS")
	private int status;
	
	@SerializedName("DES")
	private String descripcion;
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	

}
