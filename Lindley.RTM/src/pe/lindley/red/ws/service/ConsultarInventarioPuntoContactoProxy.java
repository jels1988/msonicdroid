package pe.lindley.red.ws.service;

import pe.lindley.red.ws.bean.ConsultarInventarioPuntoContactoRequest;
import pe.lindley.red.ws.bean.ConsultarInventarioPuntoContactoResponse;
import pe.lindley.util.JSONHelper;
import pe.lindley.util.ProxyBase;
import roboguice.inject.InjectResource;

public class ConsultarInventarioPuntoContactoProxy extends ProxyBase<ConsultarInventarioPuntoContactoResponse> {

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
		return urlWS + "/ConsultarInventarioPuntoContacto";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		ConsultarInventarioPuntoContactoRequest consultarInventarioPuntoContactoRequest = new ConsultarInventarioPuntoContactoRequest();
		consultarInventarioPuntoContactoRequest.setAnioMes(this.anioMes);
		consultarInventarioPuntoContactoRequest.setCodigoCliente(this.codigoCliente);
		String request = JSONHelper.serializar(consultarInventarioPuntoContactoRequest);
		return request;
	}

	@Override
	protected ConsultarInventarioPuntoContactoResponse responseText(String json) {
		// TODO Auto-generated method stub
		ConsultarInventarioPuntoContactoResponse response = JSONHelper.desSerializar(json,ConsultarInventarioPuntoContactoResponse.class);
		return response;
	}

}
