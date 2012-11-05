package lindley.desarrolloxcliente.ws.bean;

import java.util.List;

import lindley.desarrolloxcliente.to.upload.EvaluacionTO;

import com.google.gson.annotations.SerializedName;

import net.msonic.lib.ResponseBase;

public class DescargarEvaluacionResponse extends ResponseBase {

	@SerializedName("EVA")
	public List<EvaluacionTO> evaluaciones;
}
