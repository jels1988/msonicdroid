package pe.lindley.ficha.ws.service;

import pe.lindley.ficha.ws.bean.ObtenerOpcionMultipleResponse;
import pe.lindley.ficha.ws.bean.ObtenerOpcionMultipleRequest;
import pe.lindley.util.JSONHelper;
import pe.lindley.util.ProxyBase;
import roboguice.inject.InjectResource;


public class ObtenerOpcionMultipleProxy extends ProxyBase<ObtenerOpcionMultipleResponse>{
		
	@InjectResource(pe.lindley.activity.R.string.urlwsFichaOpMultiple)protected String urlWS;

	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/obtenerOpciones";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		ObtenerOpcionMultipleRequest obtenerOpcionMultipleRequest = new ObtenerOpcionMultipleRequest();		
		String request = JSONHelper.serializar(obtenerOpcionMultipleRequest);
		return request;
	}

	@Override
	protected ObtenerOpcionMultipleResponse responseText(String json) {
		// TODO Auto-generated method stub
		ObtenerOpcionMultipleResponse obtenerOpcionMultipleResponse = JSONHelper.desSerializar(json, ObtenerOpcionMultipleResponse.class);
		return obtenerOpcionMultipleResponse;
	}

	
}
