package lindley.desarrolloxcliente.ws.bean;

import com.google.gson.annotations.SerializedName;

public class ConsultarProfitRequest {

	@SerializedName("ANIO")
	public String anio;

	@SerializedName("MES")
	public String mes;

	@SerializedName("CLI")
	public String codigoCliente;

	@SerializedName("ART")
	public String codigoArticulo;
}
