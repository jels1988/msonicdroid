package lindley.desarrolloxcliente.ws.bean;

import java.util.List;

import lindley.desarrolloxcliente.to.download.TipoActivoTO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import net.msonic.lib.ResponseBase;

public class DescargarTipoActivoResponse extends ResponseBase {

	
	@Expose()
	@SerializedName("TIP")
	public List<TipoActivoTO> tipos;
}
