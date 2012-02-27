package pe.lindley.red.ws.service;

import pe.lindley.red.ws.bean.ConsultarIndiceEjecucionAnioRequest;
import pe.lindley.red.ws.bean.ConsultarIndiceEjecucionAnioResponse;
import pe.lindley.util.JSONHelper;
import pe.lindley.util.ProxyBase;
import roboguice.inject.InjectResource;

public class ConsultarIndiceEjecucionAnioProxy extends ProxyBase<ConsultarIndiceEjecucionAnioResponse> {

	@InjectResource(pe.lindley.activity.R.string.urlwsRed)protected String urlWS;
	
	private String periodo;
	
	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/ConsultarIndiceEjecucionAnio";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		ConsultarIndiceEjecucionAnioRequest consultarIndiceEjecucionAnioRequest = new ConsultarIndiceEjecucionAnioRequest();
		consultarIndiceEjecucionAnioRequest.setPeriodo(periodo);
		String request = JSONHelper.serializar(consultarIndiceEjecucionAnioRequest);
		return request;
	}

	@Override
	protected ConsultarIndiceEjecucionAnioResponse responseText(String json) {
		// TODO Auto-generated method stub
		ConsultarIndiceEjecucionAnioResponse response = JSONHelper.desSerializar(json, ConsultarIndiceEjecucionAnioResponse.class);
		return response;
	}

}
