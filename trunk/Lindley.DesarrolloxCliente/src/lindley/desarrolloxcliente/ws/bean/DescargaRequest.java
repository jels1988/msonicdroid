package lindley.desarrolloxcliente.ws.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DescargaRequest {
	@Expose()
	@SerializedName("anio")
	public int anio;
	
	@Expose()
	@SerializedName("mes")
	public int mes;
	
	@Expose()
	@SerializedName("dep")
	public String deposito;
	
	@Expose()
	@SerializedName("rut")
	public String ruta;
	
	@Expose()
	@SerializedName("cli")
	public String cliente;
}
