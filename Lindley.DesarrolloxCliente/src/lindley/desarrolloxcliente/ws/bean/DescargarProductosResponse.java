package lindley.desarrolloxcliente.ws.bean;

import java.util.List;

import net.msonic.lib.ResponseBase;

import lindley.desarrolloxcliente.to.download.ProductoTO;

import com.google.gson.annotations.SerializedName;

public class DescargarProductosResponse extends ResponseBase {

	@SerializedName("PROD")
	public List<ProductoTO> productos;
}
