package pe.lindley.red.to;

import com.google.gson.annotations.SerializedName;

public class SoviCategoriaTO {

	@SerializedName("FAM")
	private String familia;
	
	@SerializedName("MES")
	private String mes;
	
	@SerializedName("PRF")
	private double porcentajeFamilia;

	public String getFamilia() {
		return familia;
	}

	public void setFamilia(String familia) {
		this.familia = familia;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public double getPorcentajeFamilia() {
		return porcentajeFamilia;
	}

	public void setPorcentajeFamilia(double porcentajeFamilia) {
		this.porcentajeFamilia = porcentajeFamilia;
	}
	
}
