package lindley.desarrolloxcliente.ws.bean;

import com.google.gson.annotations.SerializedName;

public class ActualizarEstadoRequest {

	@SerializedName("COD")
	public String codigo;
	

	@SerializedName("EST")
	public String estado;
}
