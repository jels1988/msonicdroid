package lindley.desarrolloxcliente.ws.bean;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lindley.desarrolloxcliente.to.upload.EvaluacionTO;

public class UploadEvaluacionesRequest {
	@Expose()
	@SerializedName("EVA")
	public List<EvaluacionTO> evaluciones;
}
