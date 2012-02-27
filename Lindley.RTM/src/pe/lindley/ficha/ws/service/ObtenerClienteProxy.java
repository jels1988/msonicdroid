package pe.lindley.ficha.ws.service;

import pe.lindley.ficha.ws.bean.ObtenerClienteResponse;
import pe.lindley.ficha.ws.bean.ObtenerClienteRequest;
import pe.lindley.util.JSONHelper;
import pe.lindley.util.ProxyBase;
import roboguice.inject.InjectResource;

public class ObtenerClienteProxy extends ProxyBase<ObtenerClienteResponse>{

	@InjectResource(pe.lindley.activity.R.string.urlwsFichaCliente)protected String urlWS;
	
	private String Cliente;

	public String getCliente() {
		return Cliente;
	}

	public void setCliente(String cliente) {
		Cliente = cliente;
	}

	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/obtenerCliente";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		ObtenerClienteRequest obtenerClienteRequest = new ObtenerClienteRequest();
		obtenerClienteRequest.setCliente(Cliente);
		String request = JSONHelper.serializar(obtenerClienteRequest);
		return request;
	}

	@Override
	protected ObtenerClienteResponse responseText(String json) {
		// TODO Auto-generated method stub
		ObtenerClienteResponse obtenerClienteResponse = JSONHelper.desSerializar(json, ObtenerClienteResponse.class);
		return obtenerClienteResponse;
	}
	
}
