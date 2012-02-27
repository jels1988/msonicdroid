package pe.lindley.profit.ws.bean;

import com.google.gson.annotations.SerializedName;

public class ProfitHistoryRequest {

	@SerializedName("CodigoCliente")
	private String codigoCliente;
	
	@SerializedName("anio")
	private int anio;
	
	public String getCodigoCliente() {
		return codigoCliente;
	}
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}
	public int getAnio() {
		return anio;
	}
	public void setAnio(int anio) {
		this.anio = anio;
	}
	
}
