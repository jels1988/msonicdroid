package pe.lindley.ficha.ws.service;

import pe.lindley.ficha.ws.bean.ConsultarEncuestaRequest;
import pe.lindley.ficha.ws.bean.ConsultarEncuestaResponse;
import pe.lindley.util.JSONHelper;
import pe.lindley.util.ProxyBase;
import roboguice.inject.InjectResource;

public class ConsultarEncuestasProxy extends ProxyBase<ConsultarEncuestaResponse> {

	@InjectResource(pe.lindley.activity.R.string.urlwsFichaEncuesta)protected String urlWS;
	
	private String codigoCliente;
	
	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/ConsultarEncuestas";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		ConsultarEncuestaRequest consultarEncuestaRequest = new ConsultarEncuestaRequest();
		consultarEncuestaRequest.setCliente(codigoCliente);
		String request = JSONHelper.serializar(consultarEncuestaRequest);
		return request;
	}

	@Override
	protected ConsultarEncuestaResponse responseText(String json) {
		// TODO Auto-generated method stub
		ConsultarEncuestaResponse response = JSONHelper.desSerializar(json,ConsultarEncuestaResponse.class);
		return response;
	}

	public String getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

}
