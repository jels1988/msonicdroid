package lindley.desarrolloxcliente.ws.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import net.msonic.lib.ResponseBase;

public class ConsultarEvaluacionesAbiertasResponse extends ResponseBase {

	@Expose()
	@SerializedName("CAN")
	public int EvaluacionesAbiertas;
}
