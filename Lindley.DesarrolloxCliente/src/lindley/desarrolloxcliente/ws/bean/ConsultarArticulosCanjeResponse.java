package lindley.desarrolloxcliente.ws.bean;

import java.util.List;

import lindley.desarrolloxcliente.to.ArticuloCanjeTO;

import com.google.gson.annotations.SerializedName;

import net.msonic.lib.ResponseBase;

public class ConsultarArticulosCanjeResponse extends ResponseBase {

	@SerializedName("ART")
	public List<ArticuloCanjeTO> listArticuloCanje;
}
