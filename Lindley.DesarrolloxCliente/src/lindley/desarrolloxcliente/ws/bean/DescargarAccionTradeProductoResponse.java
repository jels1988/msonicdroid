package lindley.desarrolloxcliente.ws.bean;

import java.util.List;

import net.msonic.lib.ResponseBase;

import lindley.desarrolloxcliente.to.download.AccionTradeProductoTO;

import com.google.gson.annotations.SerializedName;

public class DescargarAccionTradeProductoResponse extends ResponseBase {
	@SerializedName("PRA")
	public List<AccionTradeProductoTO> accionesProducto;
}
