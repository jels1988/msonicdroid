package pe.lindley.red.to;

import com.google.gson.annotations.SerializedName;

public class SoviEmpresaTO {

	
	@SerializedName("CVI")
	private int carasVisibles;
	
	@SerializedName("COM")
	private String compania;
	
	@SerializedName("POR")
	private double porcentaje;

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

	public double getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(double porcentaje) {
		this.porcentaje = porcentaje;
	}

}
