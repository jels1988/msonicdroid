package lindley.desarrolloxcliente.ws.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ConsultarFotoExitoRequest {

	@Expose()
	@SerializedName("CLU")
	public String cluster;
}
