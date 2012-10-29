package lindley.desarrolloxcliente.ws.bean;

import java.util.List;

import lindley.desarrolloxcliente.to.download.OportunidadTO;

import com.google.gson.annotations.SerializedName;

import net.msonic.lib.ResponseBase;

public class DescargarOportunidadResponse extends ResponseBase {

	@SerializedName("OPO")
	public List<OportunidadTO> oportunidades;
}
