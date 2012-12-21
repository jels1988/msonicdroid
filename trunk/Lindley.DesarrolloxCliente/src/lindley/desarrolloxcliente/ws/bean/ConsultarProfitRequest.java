package lindley.desarrolloxcliente.ws.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ConsultarProfitRequest {

	@Expose()
	@SerializedName("ANIO")
	public String anio;

	@Expose()
	@SerializedName("MES")
	public String mes;

	@Expose()
	@SerializedName("CLI")
	public String codigoCliente;

	@Expose()
	@SerializedName("ART")
	public String codigoArticulo;
}
