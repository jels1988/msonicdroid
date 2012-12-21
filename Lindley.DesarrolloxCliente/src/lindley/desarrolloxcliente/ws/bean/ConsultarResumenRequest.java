package lindley.desarrolloxcliente.ws.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ConsultarResumenRequest {
	
	@Expose()
	@SerializedName("IDREG")
	public String codigoRegistro;
}
