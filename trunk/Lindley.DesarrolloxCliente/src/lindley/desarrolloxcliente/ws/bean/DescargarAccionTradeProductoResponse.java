package lindley.desarrolloxcliente.ws.bean;

import java.util.List;

import lindley.desarrolloxcliente.to.download.AccionTradeProductoTO;

import com.google.gson.annotations.SerializedName;

public class DescargarAccionTradeProductoResponse {
	@SerializedName("PRA")
	public List<AccionTradeProductoTO> accionesProducto;
}
