package pe.lindley.red.ws.service;

import pe.lindley.red.ws.bean.ConsultarSoviCategoriaRequest;
import pe.lindley.red.ws.bean.ConsultarSoviCategoriaResponse;
import pe.lindley.util.JSONHelper;
import pe.lindley.util.ProxyBase;
import roboguice.inject.InjectResource;

public class ConsultarSoviCategoriaProxy extends ProxyBase<ConsultarSoviCategoriaResponse> {
	
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
		return urlWS + "/ConsultarSoviCategoria";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		ConsultarSoviCategoriaRequest consultarSoviCategoriaRequest = new ConsultarSoviCategoriaRequest();
		consultarSoviCategoriaRequest.setAnioMes(anioMes);
		consultarSoviCategoriaRequest.setCodigoCliente(codigoCliente);
		String request = JSONHelper.serializar(consultarSoviCategoriaRequest);
		return request;
	}

	@Override
	protected ConsultarSoviCategoriaResponse responseText(String json) {
		// TODO Auto-generated method stub
		ConsultarSoviCategoriaResponse response = JSONHelper.desSerializar(json,ConsultarSoviCategoriaResponse.class);
		return response; 
	}

}
