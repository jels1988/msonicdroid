package pe.lindley.om.ws.service;

import pe.lindley.om.ws.bean.DescargarEventosOMRequest;
import pe.lindley.om.ws.bean.DescargarEventosOMResponse;
import pe.lindley.util.JSONHelper;
import pe.lindley.util.ProxyBase;
import roboguice.inject.InjectResource;

public class DescargarEventosOMProxy extends ProxyBase<DescargarEventosOMResponse> {

@InjectResource(pe.lindley.activity.R.string.urlwsOMParametros)protected String urlWS;
	
	private String codigoSap;
	
	public String getCodigoSap() {
		return codigoSap;
	}

	public void setCodigoSap(String codigoSap) {
		this.codigoSap = codigoSap;
	}

	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/SincronizarEventos";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		DescargarEventosOMRequest descargarEventosOMRequest = new DescargarEventosOMRequest();
		descargarEventosOMRequest.setCodigoSap(codigoSap);
		String request = JSONHelper.serializar(descargarEventosOMRequest);
		return request;
	}

	@Override
	protected DescargarEventosOMResponse responseText(String json) {
		// TODO Auto-generated method stub
		DescargarEventosOMResponse descargarEventosOMResponse = JSONHelper.desSerializar(json,DescargarEventosOMResponse.class);
		return descargarEventosOMResponse;
	}

}
