package pe.lindley.red.ws.service;

import pe.lindley.red.ws.bean.ConsultarSoviCoreRequest;
import pe.lindley.red.ws.bean.ConsultarSoviCoreResponse;
import pe.lindley.util.JSONHelper;
import pe.lindley.util.ProxyBase;
import roboguice.inject.InjectResource;

public class ConsultarSoviCoreProxy extends ProxyBase<ConsultarSoviCoreResponse> {

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
		return urlWS + "/ConsultarSoviCore";
	}


	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		ConsultarSoviCoreRequest consultarSoviCoreRequest = new ConsultarSoviCoreRequest();
		consultarSoviCoreRequest.setAnioMes(periodo);
		consultarSoviCoreRequest.setCodigoCliente(codigoCliente);
		
		String rq = JSONHelper.serializar(consultarSoviCoreRequest);
		return rq;
	}

	@Override
	protected ConsultarSoviCoreResponse responseText(String json) {
		// TODO Auto-generated method stub
		ConsultarSoviCoreResponse consultarSoviCoreResponse = JSONHelper.desSerializar(json, ConsultarSoviCoreResponse.class);
		return consultarSoviCoreResponse;
	}

}
