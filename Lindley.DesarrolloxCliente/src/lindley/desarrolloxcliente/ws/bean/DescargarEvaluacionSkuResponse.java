package lindley.desarrolloxcliente.ws.bean;

import java.util.List;

import net.msonic.lib.ResponseBase;

import lindley.desarrolloxcliente.to.upload.SkuTO;

import com.google.gson.annotations.SerializedName;

public class DescargarEvaluacionSkuResponse extends ResponseBase {
	
	@SerializedName("SKU")
	public List<SkuTO> skus;

}
