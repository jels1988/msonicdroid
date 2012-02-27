package pe.lindley.red.ws.service;

import pe.lindley.red.ws.bean.ConsultarEncuestaRequest;
import pe.lindley.red.ws.bean.ConsultarEncuestaResponse;
import pe.lindley.util.JSONHelper;
import pe.lindley.util.ProxyBase;
import roboguice.inject.InjectResource;

public class ConsultarEncuestaProxy extends ProxyBase<ConsultarEncuestaResponse> {

	@InjectResource(pe.lindley.activity.R.string.urlwsRed)protected String urlWS;
	
	private String codigoCliente;
	
	public String getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/ConsultarEncuesta";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		ConsultarEncuestaRequest consultarEncuestaRequest = new ConsultarEncuestaRequest();
		consultarEncuestaRequest.setCodigoCliente(codigoCliente);
		String request = JSONHelper.serializar(consultarEncuestaRequest);
		return request;
	}

	@Override
	protected ConsultarEncuestaResponse responseText(String json) {
		// TODO Auto-generated method stub
		ConsultarEncuestaResponse response = JSONHelper.desSerializar(json,ConsultarEncuestaResponse.class);
		return response;
	}

}
