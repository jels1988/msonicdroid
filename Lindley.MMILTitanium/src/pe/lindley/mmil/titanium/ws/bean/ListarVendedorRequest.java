package pe.lindley.mmil.titanium.ws.bean;

import com.google.gson.annotations.SerializedName;

public class ListarVendedorRequest {
	@SerializedName("COD")
	public String codigoDeposito;

	@SerializedName("TIP")
	public String tipo;

	@SerializedName("SUP")
	public String codigoSupervisor;
}
