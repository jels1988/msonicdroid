package pe.lindley.om.ws.service;

import pe.lindley.om.ws.bean.DescargarClientesRequest;
import pe.lindley.om.ws.bean.DescargarClientesResponse;
import pe.lindley.util.JSONHelper;
import pe.lindley.util.ProxyBase;
import roboguice.inject.InjectResource;

public class DescargarClienteProxy extends ProxyBase<DescargarClientesResponse> {

	@InjectResource(pe.lindley.activity.R.string.urlwsOMParametros)protected String urlWS;
	
	private String codigoDeposito;
	private String codigoRuta;
	
	
	public String getCodigoDeposito() {
		return codigoDeposito;
	}

	public void setCodigoDeposito(String codigoDeposito) {
		this.codigoDeposito = codigoDeposito;
	}

	public String getCodigoRuta() {
		return codigoRuta;
	}

	public void setCodigoRuta(String codigoRuta) {
		this.codigoRuta = codigoRuta;
	}

	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/SincronizarClientes";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		DescargarClientesRequest descargarClientesRequest = new DescargarClientesRequest();
		descargarClientesRequest.setCodigoDeposito(codigoDeposito);
		descargarClientesRequest.setCodigoRuta(codigoRuta);
		String request = JSONHelper.serializar(descargarClientesRequest);
		return request;
	}

	@Override
	protected DescargarClientesResponse responseText(String json) {
		// TODO Auto-generated method stub
		DescargarClientesResponse descargarClientesResponse  = JSONHelper.desSerializar(json,DescargarClientesResponse.class);
		return descargarClientesResponse;
	}

}
