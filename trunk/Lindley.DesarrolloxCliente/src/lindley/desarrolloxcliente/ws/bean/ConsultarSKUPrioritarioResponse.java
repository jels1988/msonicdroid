package lindley.desarrolloxcliente.ws.bean;

import java.util.List;

import lindley.desarrolloxcliente.to.SKUPresentacionTO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import net.msonic.lib.ResponseBase;

public class ConsultarSKUPrioritarioResponse extends ResponseBase {

	@Expose()
	@SerializedName("SKU")
	public List<SKUPresentacionTO> listaSKUPresentacion;
	
}
