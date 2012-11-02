package lindley.desarrolloxcliente.ws.bean;

import java.util.List;

import lindley.desarrolloxcliente.to.download.PresentacionTO;

import com.google.gson.annotations.SerializedName;

import net.msonic.lib.ResponseBase;

public class DescargarPresentacionResponse extends ResponseBase {

	@SerializedName("PRE")
	public List<PresentacionTO> presentaciones;
}
