package pe.lindley.ficha.ws.service;

import pe.lindley.ficha.ws.bean.ObtenerFiguraComercialResponse;
import pe.lindley.ficha.ws.bean.ObtenerFiguraComercialRequest;
import pe.lindley.util.JSONHelper;
import pe.lindley.util.ProxyBase;
import roboguice.inject.InjectResource;

public class ObtenerFiguraComercialProxy extends ProxyBase<ObtenerFiguraComercialResponse>{
	
	@InjectResource(pe.lindley.activity.R.string.urlwsFichaFigComercial)protected String urlWS;
	
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
		return urlWS + "/obtenerFiguraComercial";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		ObtenerFiguraComercialRequest obtenerFiguraComercialRequest = new ObtenerFiguraComercialRequest();
		obtenerFiguraComercialRequest.setCliente(Cliente);
		String request = JSONHelper.serializar(obtenerFiguraComercialRequest);
		return request;
	}

	@Override
	protected ObtenerFiguraComercialResponse responseText(String json) {
		// TODO Auto-generated method stub
		ObtenerFiguraComercialResponse obtenerFiguraComercialResponse = JSONHelper.desSerializar(json, ObtenerFiguraComercialResponse.class);
		return obtenerFiguraComercialResponse;
	}
	

}
