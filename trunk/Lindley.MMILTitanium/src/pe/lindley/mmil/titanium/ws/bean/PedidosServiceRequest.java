package pe.lindley.mmil.titanium.ws.bean;

import com.google.gson.annotations.SerializedName;

public class PedidosServiceRequest {

	
	@SerializedName("DEP")
	public String codigoDeposito;

	@SerializedName("VEN")
	public String codigoVendedor;
	
}
