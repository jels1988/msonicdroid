package lindley.desarrolloxcliente.ws.bean;

import java.util.List;

import lindley.desarrolloxcliente.to.upload.PosicionCompromisoTO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import net.msonic.lib.ResponseBase;

public class DescargarEvaluacionPosicionCompromisoResponse extends ResponseBase {

	@Expose()
	@SerializedName("COM")
	public List<PosicionCompromisoTO> compromisos;
}
