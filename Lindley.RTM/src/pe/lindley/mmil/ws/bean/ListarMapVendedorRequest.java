package pe.lindley.mmil.ws.bean;

import com.google.gson.annotations.SerializedName;

public class ListarMapVendedorRequest {
	
	@SerializedName("COD")
	private String codigoDeposito;

	@SerializedName("CDV")
	private int codigoVendedor;

	public String getCodigoDeposito() {
		return codigoDeposito;
	}

	public void setCodigoDeposito(String codigoDeposito) {
		this.codigoDeposito = codigoDeposito;
	}
	
	public int getCodigoVendedor() {
		return codigoVendedor;
	}

	public void setCodigoVendedor(int codigoVendedor) {
		this.codigoVendedor = codigoVendedor;
	}


}
