package lindley.desarrolloxcliente.ws.service;

import roboguice.inject.InjectResource;
import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.ws.bean.ConsultarArticulosCanjeRequest;
import lindley.desarrolloxcliente.ws.bean.ConsultarArticulosCanjeResponse;
import net.msonic.lib.JSONHelper;
import net.msonic.lib.ProxyBase;

public class ConsultarArticulosCanjeProxy extends ProxyBase<ConsultarArticulosCanjeResponse> {

	@InjectResource(R.string.urlwsDesarrolloxCliente)protected String urlWS;
	public int puntos;
	
	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/ConsultarArticulosCanje";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		ConsultarArticulosCanjeRequest consultarArticulosCanjeRequest = new ConsultarArticulosCanjeRequest();
		consultarArticulosCanjeRequest.puntos = this.puntos;
		String request = JSONHelper.serializar(consultarArticulosCanjeRequest);
		return request;
	}

	@Override
	protected ConsultarArticulosCanjeResponse responseText(String json) {
		// TODO Auto-generated method stub
		ConsultarArticulosCanjeResponse response = JSONHelper.desSerializar(json, ConsultarArticulosCanjeResponse.class);
		return response;
	}

}
