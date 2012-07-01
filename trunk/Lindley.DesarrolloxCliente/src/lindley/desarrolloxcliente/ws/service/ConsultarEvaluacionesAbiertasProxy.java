package lindley.desarrolloxcliente.ws.service;

import roboguice.inject.InjectResource;
import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.ws.bean.ConsultarEvaluacionesAbiertasRequest;
import lindley.desarrolloxcliente.ws.bean.ConsultarEvaluacionesAbiertasResponse;
import net.msonic.lib.JSONHelper;
import net.msonic.lib.ProxyBase;

public class ConsultarEvaluacionesAbiertasProxy extends ProxyBase<ConsultarEvaluacionesAbiertasResponse> {

	@InjectResource(R.string.urlwsDesarrolloxCliente)protected String urlWS;
	
	public String codigoCliente;
	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		
		return urlWS + "/ConsultarAbiertas";
		
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		ConsultarEvaluacionesAbiertasRequest consultarEvaluacionesAbiertasRequest = new ConsultarEvaluacionesAbiertasRequest();
		consultarEvaluacionesAbiertasRequest.codigoCliente=codigoCliente;
		
		String request = JSONHelper.serializar(consultarEvaluacionesAbiertasRequest);
		return request;
	}

	@Override
	protected ConsultarEvaluacionesAbiertasResponse responseText(String json) {
		// TODO Auto-generated method stub
		ConsultarEvaluacionesAbiertasResponse response = JSONHelper.desSerializar(json,ConsultarEvaluacionesAbiertasResponse.class);
		return response;
	}

}
