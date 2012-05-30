package pe.lindley.mmil.titanium.ws.bean;

import com.google.gson.annotations.SerializedName;

public class ResumenVentaRequest {

	
	@SerializedName("DEP")
	public String codigoDeposito;

	@SerializedName("SUP")
	public String codigoSupervisor;
}
