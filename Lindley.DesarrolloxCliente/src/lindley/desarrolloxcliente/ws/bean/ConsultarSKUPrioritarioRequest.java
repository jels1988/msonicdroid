package lindley.desarrolloxcliente.ws.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ConsultarSKUPrioritarioRequest {

	@Expose()
	@SerializedName("CLU")
	public String codigoCluster;
	
}
