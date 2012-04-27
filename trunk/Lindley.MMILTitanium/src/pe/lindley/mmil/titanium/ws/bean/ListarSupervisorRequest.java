package pe.lindley.mmil.titanium.ws.bean;

import com.google.gson.annotations.SerializedName;

public class ListarSupervisorRequest {

	@SerializedName("COD")
	public String codigoDeposito;

	@SerializedName("TIP")
	public int tipo;

}
