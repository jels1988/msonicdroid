package pe.lindley.mmil.ws.bean;

import com.google.gson.annotations.SerializedName;

public class ListarPoligonoMapRequest {
	
	@SerializedName("PAI")
	private String pais;

	@SerializedName("REG")
	private String region;

	@SerializedName("DEP")
	private String deposito;

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

}
