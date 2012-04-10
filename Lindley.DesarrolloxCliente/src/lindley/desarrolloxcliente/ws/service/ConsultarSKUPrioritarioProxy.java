package lindley.desarrolloxcliente.ws.service;

import roboguice.inject.InjectResource;
import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.ws.bean.ConsultarSKUPrioritarioRequest;
import lindley.desarrolloxcliente.ws.bean.ConsultarSKUPrioritarioResponse;
import net.msonic.lib.JSONHelper;
import net.msonic.lib.ProxyBase;

public class ConsultarSKUPrioritarioProxy extends ProxyBase<ConsultarSKUPrioritarioResponse> {

	@InjectResource(R.string.urlwsDesarrolloxCliente)protected String urlWS;
	
	public String codigoCluster;
	
	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/ConsultarSKUPrioritario";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		ConsultarSKUPrioritarioRequest consultarSKUPrioritarioRequest = new ConsultarSKUPrioritarioRequest();
		consultarSKUPrioritarioRequest.codigoCluster = this.codigoCluster;		
		
		String request = JSONHelper.serializar(consultarSKUPrioritarioRequest);
		return request;
	}

	@Override
	protected ConsultarSKUPrioritarioResponse responseText(String json) {
		// TODO Auto-generated method stub
		ConsultarSKUPrioritarioResponse response = JSONHelper.desSerializar(json, ConsultarSKUPrioritarioResponse.class);
		return response;
	}

}
