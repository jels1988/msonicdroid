package pe.lindley.red.ws.service;

import pe.lindley.red.ws.bean.ConsultarFichaRequest;
import pe.lindley.red.ws.bean.ConsultarFichaResponse;
import pe.lindley.util.JSONHelper;
import pe.lindley.util.ProxyBase;
import roboguice.inject.InjectResource;

public class ConsultarFichaProxy extends ProxyBase<ConsultarFichaResponse> {

	@InjectResource(pe.lindley.activity.R.string.urlwsRed)protected String urlWS;
	
	private String codigoCliente;
	private String fechaEncuesta;
	
	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/ConsultarDatosCliente";
	}

	public String getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public String getFechaEncuesta() {
		return fechaEncuesta;
	}

	public void setFechaEncuesta(String fechaEncuesta) {
		this.fechaEncuesta = fechaEncuesta;
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		ConsultarFichaRequest consultarFichaRequest = new ConsultarFichaRequest();
		consultarFichaRequest.setCodigoCliente(codigoCliente);
		consultarFichaRequest.setFechaEncuesta(fechaEncuesta);
		String request = JSONHelper.serializar(consultarFichaRequest);
		return request;
	}

	@Override
	protected ConsultarFichaResponse responseText(String json) {
		// TODO Auto-generated method stub
		ConsultarFichaResponse response = JSONHelper.desSerializar(json, ConsultarFichaResponse.class);
		return response;
	}

}
