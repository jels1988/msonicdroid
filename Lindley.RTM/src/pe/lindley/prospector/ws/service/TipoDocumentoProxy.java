package pe.lindley.prospector.ws.service;

import pe.lindley.prospector.ws.bean.TipoDocumentoRequest;
import pe.lindley.prospector.ws.bean.TipoDocumentoResponse;
import pe.lindley.util.JSONHelper;
import pe.lindley.util.ProxyBase;
import roboguice.inject.InjectResource;

public class TipoDocumentoProxy extends ProxyBase<TipoDocumentoResponse> {

	@InjectResource(pe.lindley.activity.R.string.urlwsCliente)protected String urlWS;
	
	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/TipoDocumentos";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		TipoDocumentoRequest tipoDocumentoRequest = new TipoDocumentoRequest();
		String request = JSONHelper.serializar(tipoDocumentoRequest);
		return request;
	}

	@Override
	protected TipoDocumentoResponse responseText(String json) {
		// TODO Auto-generated method stub
		TipoDocumentoResponse tipoDocumentoResponse = JSONHelper.desSerializar(json,TipoDocumentoResponse.class);
		return tipoDocumentoResponse;
	}

}
