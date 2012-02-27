package pe.lindley.om.ws.service;

import pe.lindley.om.ws.bean.DescargarParametrosRequest;
import pe.lindley.om.ws.bean.DescargarParametrosResponse;
import pe.lindley.util.JSONHelper;
import pe.lindley.util.ProxyBase;
import roboguice.inject.InjectResource;

public class DescargarParametrosProxy extends
		ProxyBase<DescargarParametrosResponse> {

	@InjectResource(pe.lindley.activity.R.string.urlwsOMParametros)protected String urlWS;
	
	private String codigoAlmacen;
	
	public String getCodigoAlmancen() {
		return codigoAlmacen;
	}

	public void setCodigoAlmancen(String codigoAlmancen) {
		this.codigoAlmacen = codigoAlmancen;
	}

	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/SincronizarParametros";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		
		DescargarParametrosRequest descargarParametrosRequest = new DescargarParametrosRequest();
		descargarParametrosRequest.setCodigoAlmacen(codigoAlmacen);
		String request = JSONHelper.serializar(descargarParametrosRequest);
		return request;
	}

	@Override
	protected DescargarParametrosResponse responseText(String json) {
		// TODO Auto-generated method stub
		DescargarParametrosResponse descargarParametrosResponse = JSONHelper.desSerializar(json,DescargarParametrosResponse.class);
		return descargarParametrosResponse;
	}

}
