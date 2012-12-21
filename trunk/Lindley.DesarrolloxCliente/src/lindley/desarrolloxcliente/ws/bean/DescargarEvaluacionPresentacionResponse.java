package lindley.desarrolloxcliente.ws.bean;

import java.util.List;

import lindley.desarrolloxcliente.to.upload.PresentacionTO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import net.msonic.lib.ResponseBase;

public class DescargarEvaluacionPresentacionResponse  extends ResponseBase {
	@Expose()
	@SerializedName("PRE")
	public List<PresentacionTO> presentaciones;
}
