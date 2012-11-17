package lindley.desarrolloxcliente.ws.bean;

import java.util.List;

import lindley.desarrolloxcliente.to.download.AceleradorTO;

import com.google.gson.annotations.SerializedName;

import net.msonic.lib.ResponseBase;

public class DescargarAceleradorResponse extends ResponseBase {

	@SerializedName("ACE")
	public List<AceleradorTO> aceleradores;
}
