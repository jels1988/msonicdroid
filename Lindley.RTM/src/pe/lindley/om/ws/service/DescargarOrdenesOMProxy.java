package pe.lindley.om.ws.service;

import pe.lindley.om.ws.bean.DescargarOrdenesOMRequest;
import pe.lindley.om.ws.bean.DescargarOrdenesOMResponse;
import pe.lindley.util.JSONHelper;
import pe.lindley.util.ProxyBase;
import roboguice.inject.InjectResource;

public class DescargarOrdenesOMProxy extends ProxyBase<DescargarOrdenesOMResponse> {
	
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
		return urlWS + "/SincronizarOrdenes";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		DescargarOrdenesOMRequest descargarOrdenesOMRequest = new DescargarOrdenesOMRequest();
		descargarOrdenesOMRequest.setCodigoSap(codigoSap);
		String request = JSONHelper.serializar(descargarOrdenesOMRequest);
		return request;
	}

	@Override
	protected DescargarOrdenesOMResponse responseText(String json) {
		// TODO Auto-generated method stub
		DescargarOrdenesOMResponse descargarOrdenesOMResponse = JSONHelper.desSerializar(json,DescargarOrdenesOMResponse.class);
		return descargarOrdenesOMResponse;
	}

}
