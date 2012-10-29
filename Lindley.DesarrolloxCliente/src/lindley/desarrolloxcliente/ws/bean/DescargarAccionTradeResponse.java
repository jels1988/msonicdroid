package lindley.desarrolloxcliente.ws.bean;

import java.util.List;

import lindley.desarrolloxcliente.to.download.AccionTradeTO;

import com.google.gson.annotations.SerializedName;

public class DescargarAccionTradeResponse {

	@SerializedName("TRA")
	public List<AccionTradeTO> acciones;
}
