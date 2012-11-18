package lindley.desarrolloxcliente.ws.bean;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import lindley.desarrolloxcliente.to.upload.EvaluacionProcessUploadTO;

import net.msonic.lib.ResponseBase;

public class UploadEvaluacionesResponse extends ResponseBase {
	
	@SerializedName("IDS")
	public List<EvaluacionProcessUploadTO> ids;
}
