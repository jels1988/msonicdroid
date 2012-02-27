package pe.lindley.red.ws.service;

import pe.lindley.red.ws.bean.ConsultarIndiceEjecucionMatrizRequest;
import pe.lindley.red.ws.bean.ConsultarIndiceEjecucionMatrizResponse;
import pe.lindley.util.JSONHelper;
import pe.lindley.util.ProxyBase;
import roboguice.inject.InjectResource;

public class ConsultarIndiceEjecucionMatrizProxy extends ProxyBase<ConsultarIndiceEjecucionMatrizResponse> {

	@InjectResource(pe.lindley.activity.R.string.urlwsRed)protected String urlWS;
	
	
	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/ConsultarIndiceEjecucionMatriz";
	}

	private String periodo;
	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	
	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		ConsultarIndiceEjecucionMatrizRequest consultarIndiceEjecucionMatrizRequest = new ConsultarIndiceEjecucionMatrizRequest();
		consultarIndiceEjecucionMatrizRequest.setAnioMes(this.periodo);
		
		String request = JSONHelper.serializar(consultarIndiceEjecucionMatrizRequest);
		return request;
	}

	@Override
	protected ConsultarIndiceEjecucionMatrizResponse responseText(String json) {
		// TODO Auto-generated method stub
		ConsultarIndiceEjecucionMatrizResponse response = JSONHelper.desSerializar(json, ConsultarIndiceEjecucionMatrizResponse.class);
		return response;
	}

}
