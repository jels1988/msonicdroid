package pe.lindley.puntointeres.ws.service;

import pe.lindley.puntointeres.ws.bean.ObtenerParametroRequest;
import pe.lindley.puntointeres.ws.bean.ObtenerParametroResponse;
import pe.lindley.util.JSONHelper;
import pe.lindley.util.ProxyBase;
import roboguice.inject.InjectResource;

public class ObtenerParametroProxy extends ProxyBase<ObtenerParametroResponse>{

	@InjectResource(pe.lindley.activity.R.string.urlwsPINT)protected String urlWS;
	
	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/ListarParametro";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		ObtenerParametroRequest obtenerParametroResponse = new ObtenerParametroRequest();		
		String request = JSONHelper.serializar(obtenerParametroResponse);
		return request;
	}

	@Override
	protected ObtenerParametroResponse responseText(String json) {
		// TODO Auto-generated method stub
		ObtenerParametroResponse obtenerParametroResponse = JSONHelper.desSerializar(json, ObtenerParametroResponse.class);
		return obtenerParametroResponse;
	}

}
