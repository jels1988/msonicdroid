package pe.lindley.red.to;

import com.google.gson.annotations.SerializedName;

public class SKUPropietarioTO {

	@SerializedName("STA")
	private short staSus;
	
	@SerializedName("EST")
	private short estado;
	
	@SerializedName("PRO")
	private String producto;
	
	@SerializedName("SUS")
	private String sustituto;

	public short getStaSus() {
		return staSus;
	}

	public void setStaSus(short staSus) {
		this.staSus = staSus;
	}

	public short getEstado() {
		return estado;
	}

	public void setEstado(short estado) {
		this.estado = estado;
	}

	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public String getSustituto() {
		return sustituto;
	}

	public void setSustituto(String sustituto) {
		this.sustituto = sustituto;
	}
}
