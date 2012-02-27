package pe.lindley.red.ws.service;

import pe.lindley.red.ws.bean.ConsultarInventarioPrecioMarcadoRequest;
import pe.lindley.red.ws.bean.ConsultarInventarioPrecioMarcadoResponse;
import pe.lindley.util.JSONHelper;
import pe.lindley.util.ProxyBase;
import roboguice.inject.InjectResource;

public class ConsultarInventarioPrecioMarcadoProxy extends ProxyBase<ConsultarInventarioPrecioMarcadoResponse>{

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
		return urlWS + "/ConsultarInventarioPrecioMarcado";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		ConsultarInventarioPrecioMarcadoRequest consultarInventarioPrecioMarcadoRequest = new ConsultarInventarioPrecioMarcadoRequest();
		consultarInventarioPrecioMarcadoRequest.setAnioMes(this.anioMes);
		consultarInventarioPrecioMarcadoRequest.setCodigoCliente(this.codigoCliente);
		String request = JSONHelper.serializar(consultarInventarioPrecioMarcadoRequest);
		return request;
	}

	@Override
	protected ConsultarInventarioPrecioMarcadoResponse responseText(String json) {
		// TODO Auto-generated method stub
		ConsultarInventarioPrecioMarcadoResponse response = JSONHelper.desSerializar(json,ConsultarInventarioPrecioMarcadoResponse.class);
		return response; 
	}

}
