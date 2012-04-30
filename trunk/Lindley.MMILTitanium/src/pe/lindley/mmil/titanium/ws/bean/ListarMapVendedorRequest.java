package pe.lindley.mmil.titanium.ws.bean;

import com.google.gson.annotations.SerializedName;

public class ListarMapVendedorRequest {

	@SerializedName("COD")
	public String codigoDeposito;

	@SerializedName("CDV")
	public String codigoVendedor;

}
