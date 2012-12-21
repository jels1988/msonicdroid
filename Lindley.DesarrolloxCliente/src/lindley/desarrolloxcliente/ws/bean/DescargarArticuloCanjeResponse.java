package lindley.desarrolloxcliente.ws.bean;

import java.util.List;

import lindley.desarrolloxcliente.to.download.ArticuloTO;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import net.msonic.lib.ResponseBase;

public class DescargarArticuloCanjeResponse extends ResponseBase {
	
	
	@Expose()
	@SerializedName("ART")
	public List<ArticuloTO> articulo;

}
