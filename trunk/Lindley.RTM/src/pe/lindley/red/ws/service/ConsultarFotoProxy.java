package pe.lindley.red.ws.service;

import pe.lindley.red.ws.bean.ConsultarFotoRequest;
import pe.lindley.red.ws.bean.ConsultarFotoResponse;
import pe.lindley.util.JSONHelper;
import pe.lindley.util.ProxyBase;
import roboguice.inject.InjectResource;

public class ConsultarFotoProxy extends ProxyBase<ConsultarFotoResponse>  {
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
		
		return urlWS + "/ConsultarImagen";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		ConsultarFotoRequest consultarFotoRequest = new ConsultarFotoRequest();
		consultarFotoRequest.setPeriodo(periodo);
		String request = JSONHelper.serializar(consultarFotoRequest);
		return request;
	}

	@Override
	protected ConsultarFotoResponse responseText(String json) {
		// TODO Auto-generated method stub
		ConsultarFotoResponse consultarFotoResponse = JSONHelper.desSerializar(json,ConsultarFotoResponse.class);
		return consultarFotoResponse;
	}

}
