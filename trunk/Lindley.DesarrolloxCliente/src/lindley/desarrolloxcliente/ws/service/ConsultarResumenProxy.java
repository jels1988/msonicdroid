package lindley.desarrolloxcliente.ws.service;

import roboguice.inject.InjectResource;
import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.ws.bean.ConsultarResumenRequest;
import lindley.desarrolloxcliente.ws.bean.ConsultarResumenResponse;
import net.msonic.lib.JSONHelper;
import net.msonic.lib.ProxyBase;

public class ConsultarResumenProxy extends ProxyBase<ConsultarResumenResponse> {

	
	@InjectResource(R.string.urlwsDesarrolloxCliente)protected String urlWS;
	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/ConsultarResumen";
	}
	
	public String codigoRegistro;

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		
		ConsultarResumenRequest consultarResumenRequest = new ConsultarResumenRequest();
		consultarResumenRequest.codigoRegistro = codigoRegistro;
		String request = JSONHelper.serializar(consultarResumenRequest);
		return request;
	}

	@Override
	protected ConsultarResumenResponse responseText(String json) {
		// TODO Auto-generated method stub
		
		ConsultarResumenResponse response = JSONHelper.desSerializar(json,ConsultarResumenResponse.class);
		return response;
	}

}
