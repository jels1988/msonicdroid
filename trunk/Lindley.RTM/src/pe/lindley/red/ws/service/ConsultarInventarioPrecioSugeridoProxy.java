package pe.lindley.red.ws.service;

import pe.lindley.red.ws.bean.ConsultarInventarioPrecioSugeridoRequest;
import pe.lindley.red.ws.bean.ConsultarInventarioPrecioSugeridoResponse;
import pe.lindley.util.JSONHelper;
import pe.lindley.util.ProxyBase;
import roboguice.inject.InjectResource;

public class ConsultarInventarioPrecioSugeridoProxy extends ProxyBase<ConsultarInventarioPrecioSugeridoResponse> {

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
		return urlWS + "/ConsultarInventarioPrecioSugerido";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		ConsultarInventarioPrecioSugeridoRequest consultarInventarioPrecioSugeridoRequest = new ConsultarInventarioPrecioSugeridoRequest();
		consultarInventarioPrecioSugeridoRequest.setAnioMes(this.anioMes);
		consultarInventarioPrecioSugeridoRequest.setCodigoCliente(this.codigoCliente);
		String request = JSONHelper.serializar(consultarInventarioPrecioSugeridoRequest);
		return request;
	}

	@Override
	protected ConsultarInventarioPrecioSugeridoResponse responseText(String json) {
		// TODO Auto-generated method stub
		ConsultarInventarioPrecioSugeridoResponse response = JSONHelper.desSerializar(json,ConsultarInventarioPrecioSugeridoResponse.class);
		return response;
	}

}
