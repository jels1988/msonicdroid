package lindley.desarrolloxcliente.ws.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ActualizarEstadoRequest {

	@Expose()
	@SerializedName("COD")
	public String codigo;
	

	@Expose()
	@SerializedName("EST")
	public String estado;
}
