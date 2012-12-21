package lindley.desarrolloxcliente.ws.bean;

import java.util.List;

import lindley.desarrolloxcliente.to.NuevaOportunidadTO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import net.msonic.lib.ResponseBase;

public class ConsultarNuevaOportunidadResponse extends ResponseBase {

	@Expose()
	@SerializedName("OPR")
	public List<NuevaOportunidadTO> listaNuevaOportunidad;
	
}
 