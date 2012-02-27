package pe.lindley.red.ws.service;

import pe.lindley.red.ws.bean.ConsultarOrdenTrabajoRequest;
import pe.lindley.red.ws.bean.ConsultarOrdenTrabajoResponse;
import pe.lindley.util.JSONHelper;
import pe.lindley.util.ProxyBase;
import roboguice.inject.InjectResource;

public class ConsultarOrdenTrabajoProxy extends ProxyBase<ConsultarOrdenTrabajoResponse>{

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
		return urlWS + "/ConsultarOrdenTrabajo";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		ConsultarOrdenTrabajoRequest consultarOrdenTrabajoRequest = new ConsultarOrdenTrabajoRequest();
		consultarOrdenTrabajoRequest.setAnioMes(this.anioMes);
		consultarOrdenTrabajoRequest.setCodigoCliente(this.codigoCliente);
		String request = JSONHelper.serializar(consultarOrdenTrabajoRequest);
		return request;
	}

	@Override
	protected ConsultarOrdenTrabajoResponse responseText(String json) {
		// TODO Auto-generated method stub
		ConsultarOrdenTrabajoResponse response = JSONHelper.desSerializar(json,ConsultarOrdenTrabajoResponse.class);
		return response;
	}

}
