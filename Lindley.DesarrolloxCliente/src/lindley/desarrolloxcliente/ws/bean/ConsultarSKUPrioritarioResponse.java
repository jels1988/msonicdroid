package lindley.desarrolloxcliente.ws.bean;

import java.util.List;

import lindley.desarrolloxcliente.to.SKUPresentacionTO;

import com.google.gson.annotations.SerializedName;

import net.msonic.lib.ResponseBase;

public class ConsultarSKUPrioritarioResponse extends ResponseBase {

	@SerializedName("SKU")
	public List<SKUPresentacionTO> listaSKUPresentacion;
	
}
