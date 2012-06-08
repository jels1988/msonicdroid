package pe.lindley.mmil.titanium.ws.bean;

import com.google.gson.annotations.SerializedName;

public class ConsultarClienteRequest {

	@SerializedName("DEP")
	public String codigoDeposito;

	@SerializedName("CLI")
	public String codigoCliente;
}
