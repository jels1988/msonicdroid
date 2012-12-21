package lindley.desarrolloxcliente.ws.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ConsultarArticulosCanjeRequest {

	@Expose()
	@SerializedName("PTO")
	public int puntos;
}
