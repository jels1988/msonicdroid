package pe.lindley.red.to;

import com.google.gson.annotations.SerializedName;

public class SoviCoreTO {
	
	@SerializedName("CVI")
	private int carasVisibles;
	
	@SerializedName("MAR")
	private String marca;
	
	@SerializedName("MES")
	private String mes;

	public int getCarasVisibles() {
		return carasVisibles;
	}

	public void setCarasVisibles(int carasVisibles) {
		this.carasVisibles = carasVisibles;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}
	

}
