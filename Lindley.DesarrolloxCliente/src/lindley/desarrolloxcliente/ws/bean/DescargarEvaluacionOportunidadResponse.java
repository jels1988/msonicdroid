package lindley.desarrolloxcliente.ws.bean;

import java.util.List;

import lindley.desarrolloxcliente.to.upload.OportunidadTO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import net.msonic.lib.ResponseBase;

public class DescargarEvaluacionOportunidadResponse extends ResponseBase {
	
	@Expose()
	@SerializedName("OPO")
	public List<OportunidadTO> oportunidades;
}
