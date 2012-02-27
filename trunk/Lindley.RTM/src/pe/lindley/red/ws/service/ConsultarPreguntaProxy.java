package pe.lindley.red.ws.service;

import pe.lindley.red.ws.bean.ConsultarPreguntaRequest;
import pe.lindley.red.ws.bean.ConsultarPreguntaResponse;
import pe.lindley.util.JSONHelper;
import pe.lindley.util.ProxyBase;
import roboguice.inject.InjectResource;

public class ConsultarPreguntaProxy extends	ProxyBase<ConsultarPreguntaResponse> {

	@InjectResource(pe.lindley.activity.R.string.urlwsRed)protected String urlWS;
	
	private String codigoCliente;
	private String anioMes;

	public String getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public String getAnioMes() {
		return anioMes;
	}

	public void setAnioMes(String anioMes) {
		this.anioMes = anioMes;
	}
	
	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/ConsultarPregunta";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		ConsultarPreguntaRequest consultarPreguntaRequest = new ConsultarPreguntaRequest();
		consultarPreguntaRequest.setAnioMes(this.anioMes);
		consultarPreguntaRequest.setCodigoCliente(this.codigoCliente);
		String request = JSONHelper.serializar(consultarPreguntaRequest);
		return request;
	}

	@Override
	protected ConsultarPreguntaResponse responseText(String json) {
		// TODO Auto-generated method stub
		ConsultarPreguntaResponse response = JSONHelper.desSerializar(json,ConsultarPreguntaResponse.class);
		return response;
	}

}
