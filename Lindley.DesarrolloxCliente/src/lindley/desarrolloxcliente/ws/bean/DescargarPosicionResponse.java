package lindley.desarrolloxcliente.ws.bean;

import java.util.List;

import lindley.desarrolloxcliente.to.download.PosicionTO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import net.msonic.lib.ResponseBase;

public class DescargarPosicionResponse extends ResponseBase {
	
	@Expose()
	@SerializedName("POS")
	public List<PosicionTO> posiciones;

}
