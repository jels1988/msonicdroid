package pe.lindley.profit.ws.bean;

import com.google.gson.annotations.SerializedName;

public class ProfitHistoryDetalleRequest {

	@SerializedName("CodigoCliente")
	private String codigoCliente;

	public String getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}
}
