package pe.lindley.red.ws.service;

import pe.lindley.red.ws.bean.ConsultarSoviPackageRequest;
import pe.lindley.red.ws.bean.ConsultarSoviPackageResponse;
import pe.lindley.util.JSONHelper;
import pe.lindley.util.ProxyBase;
import roboguice.inject.InjectResource;

public class ConsultarSoviPackageProxy extends ProxyBase<ConsultarSoviPackageResponse> {

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
		return urlWS + "/ConsultarSoviPackage";
	}


	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		ConsultarSoviPackageRequest consultarSoviPackageRequest = new ConsultarSoviPackageRequest();
		consultarSoviPackageRequest.setAnioMes(periodo);
		consultarSoviPackageRequest.setCodigoCliente(codigoCliente);
		
		String rq = JSONHelper.serializar(consultarSoviPackageRequest);
		return rq;
		
	}

	@Override
	protected ConsultarSoviPackageResponse responseText(String json) {
		// TODO Auto-generated method stub
		ConsultarSoviPackageResponse consultarSoviPackageResponse = JSONHelper.desSerializar(json, ConsultarSoviPackageResponse.class);
		return consultarSoviPackageResponse;
	}

}
