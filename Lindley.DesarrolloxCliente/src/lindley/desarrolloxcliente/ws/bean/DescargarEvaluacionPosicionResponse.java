package lindley.desarrolloxcliente.ws.bean;

import java.util.List;

import lindley.desarrolloxcliente.to.upload.PosicionTO;

import com.google.gson.annotations.SerializedName;

import net.msonic.lib.ResponseBase;

public class DescargarEvaluacionPosicionResponse extends ResponseBase {

	@SerializedName("POS")
	public List<PosicionTO> posiciones;
}
