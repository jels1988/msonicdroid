package lindley.desarrolloxcliente.ws.bean;

import java.util.List;

import lindley.desarrolloxcliente.to.download.SkuTO;

import com.google.gson.annotations.SerializedName;

import net.msonic.lib.ResponseBase;

public class DescargarSkuResponse extends ResponseBase {

	@SerializedName("SKU")
	public List<SkuTO> skus;
}
