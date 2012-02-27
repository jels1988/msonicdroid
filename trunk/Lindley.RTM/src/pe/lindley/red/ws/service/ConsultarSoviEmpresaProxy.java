package pe.lindley.red.ws.service;

import pe.lindley.red.ws.bean.ConsultarSoviEmpresaRequest;
import pe.lindley.red.ws.bean.ConsultarSoviEmpresaResponse;
import pe.lindley.util.JSONHelper;
import pe.lindley.util.ProxyBase;
import roboguice.inject.InjectResource;

public class ConsultarSoviEmpresaProxy extends ProxyBase<ConsultarSoviEmpresaResponse> {

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
		return urlWS + "/ConsultarSoviEmpresa";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		ConsultarSoviEmpresaRequest consultarSoviEmpresaRequest = new ConsultarSoviEmpresaRequest();
		consultarSoviEmpresaRequest.setAnioMes(periodo);
		consultarSoviEmpresaRequest.setCodigoCliente(codigoCliente);
		
		String request = JSONHelper.serializar(consultarSoviEmpresaRequest);
		
		return request;
	}

	@Override
	protected ConsultarSoviEmpresaResponse responseText(String json) {
		// TODO Auto-generated method stub
		ConsultarSoviEmpresaResponse response = JSONHelper.desSerializar(json, ConsultarSoviEmpresaResponse.class);
		return response;
	}

}
