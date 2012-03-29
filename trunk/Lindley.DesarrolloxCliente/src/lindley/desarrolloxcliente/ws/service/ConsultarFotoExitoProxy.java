package lindley.desarrolloxcliente.ws.service;

import roboguice.inject.InjectResource;
import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.ws.bean.ConsultarFotoExitoRequest;
import lindley.desarrolloxcliente.ws.bean.ConsultarFotoExitoResponse;
import net.msonic.lib.JSONHelper;
import net.msonic.lib.ProxyBase;

public class ConsultarFotoExitoProxy extends ProxyBase<ConsultarFotoExitoResponse> {

	@InjectResource(R.string.urlwsDesarrolloxCliente)protected String urlWS;
	public String cluster;
	
	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/ConsultarFotoExito";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		ConsultarFotoExitoRequest consultarFotoExitoRequest = new ConsultarFotoExitoRequest();
		consultarFotoExitoRequest.cluster = this.cluster;
		
		String request = JSONHelper.serializar(consultarFotoExitoRequest);
		return request;
	}

	@Override
	protected ConsultarFotoExitoResponse responseText(String json) {
		// TODO Auto-generated method stub
		ConsultarFotoExitoResponse response = JSONHelper.desSerializar(json, ConsultarFotoExitoResponse.class);
		return response;
	}

}
