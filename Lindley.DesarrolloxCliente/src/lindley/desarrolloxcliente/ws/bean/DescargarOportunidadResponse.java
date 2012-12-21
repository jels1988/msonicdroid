package lindley.desarrolloxcliente.ws.bean;

import java.util.List;

import lindley.desarrolloxcliente.to.download.OportunidadTO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import net.msonic.lib.ResponseBase;

public class DescargarOportunidadResponse extends ResponseBase {

	@Expose()
	@SerializedName("OPO")
	public List<OportunidadTO> oportunidades;
}
