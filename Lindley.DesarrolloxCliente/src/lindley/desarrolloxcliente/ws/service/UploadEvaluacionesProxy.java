package lindley.desarrolloxcliente.ws.service;

import com.google.inject.Inject;

import roboguice.inject.InjectResource;
import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.negocio.UploadBLL;
import lindley.desarrolloxcliente.ws.bean.UploadEvaluacionesRequest;
import lindley.desarrolloxcliente.ws.bean.UploadEvaluacionesResponse;
import net.msonic.lib.JSONHelper;
import net.msonic.lib.ProxyBase;

public class UploadEvaluacionesProxy extends ProxyBase<UploadEvaluacionesResponse>{


	@InjectResource(R.string.urlwsUploadService)protected String urlWS;
	@Inject UploadBLL uploadBLL;
	
	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/upEvaluacion";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		
		UploadEvaluacionesRequest uploadEvaluacionesRequest = new UploadEvaluacionesRequest();
		uploadEvaluacionesRequest.evaluciones = uploadBLL.listarEvaluaciones();
		
		String request = JSONHelper.serializar(uploadEvaluacionesRequest);
		return request;
	}

	@Override
	protected UploadEvaluacionesResponse responseText(String json) {
		// TODO Auto-generated method stub
		UploadEvaluacionesResponse response = JSONHelper.desSerializar(json, UploadEvaluacionesResponse.class);
		return response;
	}

}
