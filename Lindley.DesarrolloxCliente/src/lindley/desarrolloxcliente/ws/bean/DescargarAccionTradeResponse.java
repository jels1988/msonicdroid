package lindley.desarrolloxcliente.ws.bean;

import java.util.List;

import net.msonic.lib.ResponseBase;

import lindley.desarrolloxcliente.to.download.AccionTradeTO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DescargarAccionTradeResponse extends ResponseBase {

	@Expose()
	@SerializedName("TRA")
	public List<AccionTradeTO> acciones;
}
