package lindley.desarrolloxcliente.ws.bean;

import com.google.gson.annotations.SerializedName;

public class DescargaRequest {
	@SerializedName("anio")
	public int anio;
	@SerializedName("mes")
	public int mes;
	@SerializedName("dep")
	public String deposito;
	@SerializedName("rut")
	public String ruta;
	
	@SerializedName("cli")
	public String cliente;
}
