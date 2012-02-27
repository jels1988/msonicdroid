package pe.lindley.red.ws.service;

import pe.lindley.red.ws.bean.ConsultarIndiceEjecucionClienteRequest;
import pe.lindley.red.ws.bean.ConsultarIndiceEjecucionClienteResponse;
import pe.lindley.util.JSONHelper;
import pe.lindley.util.ProxyBase;
import roboguice.inject.InjectResource;

public class ConsultarIndiceEjecucionClienteProxy extends ProxyBase<ConsultarIndiceEjecucionClienteResponse> {

	@InjectResource(pe.lindley.activity.R.string.urlwsRed)protected String urlWS;
	
	
	private String periodo;
	private String codigoCliente;
	
	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public String getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	
	
	
	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/ConsultarIndiceEjecucionCliente";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		ConsultarIndiceEjecucionClienteRequest consultarIndiceEjecucionClienteRequest = new ConsultarIndiceEjecucionClienteRequest();
		consultarIndiceEjecucionClienteRequest.setCodigoCliente(codigoCliente);
		consultarIndiceEjecucionClienteRequest.setAnioMes(periodo);
		
		String request = JSONHelper.serializar(consultarIndiceEjecucionClienteRequest);
		return request;
		
	}

	@Override
	protected ConsultarIndiceEjecucionClienteResponse responseText(String json) {
		// TODO Auto-generated method stub
		ConsultarIndiceEjecucionClienteResponse rs = JSONHelper.desSerializar(json,ConsultarIndiceEjecucionClienteResponse.class);
		return rs;
	}

}
