package pe.lindley.ficha.ws.service;

import pe.lindley.ficha.ws.bean.ObtenerComercialResponse;
import pe.lindley.ficha.ws.bean.ObtenerComercialRequest;
import pe.lindley.util.JSONHelper;
import pe.lindley.util.ProxyBase;
import roboguice.inject.InjectResource;

public class ObtenerComercialProxy extends ProxyBase<ObtenerComercialResponse>{

	@InjectResource(pe.lindley.activity.R.string.urlwsFichaComercial)protected String urlWS;
	
	private String cliente;
	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	
	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/obtenerComercial";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		ObtenerComercialRequest obtenerComercialRequest = new ObtenerComercialRequest();
		obtenerComercialRequest.setCodigo(cliente);
		String request = JSONHelper.serializar(obtenerComercialRequest);
		return request;
	}

	@Override
	protected ObtenerComercialResponse responseText(String json) {
		// TODO Auto-generated method stub
		ObtenerComercialResponse ObtenerComercialResponse = JSONHelper.desSerializar(json, ObtenerComercialResponse.class);
		return ObtenerComercialResponse;
	}

}
