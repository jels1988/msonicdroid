package pe.lindley.red.ws.service;

import pe.lindley.red.ws.bean.ConsultarComunicacionRequest;
import pe.lindley.red.ws.bean.ConsultarComunicacionResponse;
import pe.lindley.util.JSONHelper;
import pe.lindley.util.ProxyBase;
import roboguice.inject.InjectResource;

public class ConsultarComunicacionProxy extends ProxyBase<ConsultarComunicacionResponse>{

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
		return urlWS + "/ConsultarComunicacion";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		ConsultarComunicacionRequest consultarComunicacionRequest = new ConsultarComunicacionRequest();
		consultarComunicacionRequest.setAnioMes(this.anioMes);
		consultarComunicacionRequest.setCodigoCliente(this.codigoCliente);
		String request = JSONHelper.serializar(consultarComunicacionRequest);
		return request;
	}

	@Override
	protected ConsultarComunicacionResponse responseText(String json) {
		// TODO Auto-generated method stub
		ConsultarComunicacionResponse response = JSONHelper.desSerializar(json,ConsultarComunicacionResponse.class);
		return response;
	}

	
	
}
