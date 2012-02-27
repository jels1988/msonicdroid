package pe.lindley.plandesarrollo.ws.service;

import pe.lindley.plandesarrollo.ws.bean.DescargarParametrosRequest;
import pe.lindley.plandesarrollo.ws.bean.DescargarParametrosResponse;
import pe.lindley.util.JSONHelper;
import pe.lindley.util.ProxyBase;
import roboguice.inject.InjectResource;

public class DescargarParametrosProxy extends ProxyBase<DescargarParametrosResponse> {

	@InjectResource(pe.lindley.activity.R.string.urlwsPDParametro) protected String urlWS;
	
	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/DescargarParametro";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		DescargarParametrosRequest descargarParametrosRequest = new DescargarParametrosRequest();
		String request = JSONHelper.serializar(descargarParametrosRequest);
		return request;
	}

	@Override
	protected DescargarParametrosResponse responseText(String json) {
		// TODO Auto-generated method stub
		DescargarParametrosResponse descargarParametrosRequest = JSONHelper.desSerializar(json, DescargarParametrosResponse.class); 
		return descargarParametrosRequest;
	}

}
