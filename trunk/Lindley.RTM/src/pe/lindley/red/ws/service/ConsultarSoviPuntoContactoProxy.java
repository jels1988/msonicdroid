package pe.lindley.red.ws.service;

import pe.lindley.red.ws.bean.ConsultarSoviPuntoContactoRequest;
import pe.lindley.red.ws.bean.ConsultarSoviPuntoContactoResponse;
import pe.lindley.util.JSONHelper;
import pe.lindley.util.ProxyBase;
import roboguice.inject.InjectResource;

public class ConsultarSoviPuntoContactoProxy extends ProxyBase<ConsultarSoviPuntoContactoResponse> {

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
		return urlWS + "/ConsultarPuntoContacto";
	}


	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		ConsultarSoviPuntoContactoRequest consultarSoviPuntoContactoRequest = new ConsultarSoviPuntoContactoRequest();
		consultarSoviPuntoContactoRequest.setAnioMes(periodo);
		consultarSoviPuntoContactoRequest.setCodigoCliente(codigoCliente);
		
		String resquest = JSONHelper.serializar(consultarSoviPuntoContactoRequest);
		return resquest;
	}

	@Override
	protected ConsultarSoviPuntoContactoResponse responseText(String json) {
		// TODO Auto-generated method stub
		ConsultarSoviPuntoContactoResponse response = JSONHelper.desSerializar(json, ConsultarSoviPuntoContactoResponse.class);
		return response;
	}

}
