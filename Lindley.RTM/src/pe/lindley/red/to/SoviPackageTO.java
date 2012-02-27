package pe.lindley.red.to;

import com.google.gson.annotations.SerializedName;

public class SoviPackageTO {

	@SerializedName("CVI")
	private int carasVisibles;
	
	@SerializedName("COM")
	private String compania;
	

	@SerializedName("TPD")
	private String tipoProducto;

	
	public int getCarasVisibles() {
		return carasVisibles;
	}

	public void setCarasVisibles(int carasVisibles) {
		this.carasVisibles = carasVisibles;
	}

	public String getCompania() {
		return compania;
	}

	public void setCompania(String compania) {
		this.compania = compania;
	}

	public String getTipoProducto() {
		return tipoProducto;
	}

	public void setTipoProducto(String tipoProducto) {
		this.tipoProducto = tipoProducto;
	}

	
}
