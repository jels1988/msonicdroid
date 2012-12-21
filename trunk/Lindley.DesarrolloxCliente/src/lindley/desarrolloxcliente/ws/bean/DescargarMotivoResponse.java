package lindley.desarrolloxcliente.ws.bean;

import java.util.List;

import lindley.desarrolloxcliente.to.ResumenValueTO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import net.msonic.lib.ResponseBase;

public class DescargarMotivoResponse extends ResponseBase {

	@Expose()
	@SerializedName("MOT")
	public List<ResumenValueTO> motivos;
}
