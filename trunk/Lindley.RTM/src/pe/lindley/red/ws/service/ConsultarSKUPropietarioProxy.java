package pe.lindley.red.ws.service;

import pe.lindley.red.ws.bean.ConsultarSKUPropietarioRequest;
import pe.lindley.red.ws.bean.ConsultarSKUPropietarioResponse;
import pe.lindley.util.JSONHelper;
import pe.lindley.util.ProxyBase;
import roboguice.inject.InjectResource;

public class ConsultarSKUPropietarioProxy extends ProxyBase<ConsultarSKUPropietarioResponse> {

@InjectResource(pe.lindley.activity.R.string.urlwsRed)protected String urlWS;
	
	private String codigoCliente;
	private String fechaEncuesta;
	
	
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
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/ConsultarSKUPropietario";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		ConsultarSKUPropietarioRequest consultarSKUPropietarioRequest = new ConsultarSKUPropietarioRequest();
		consultarSKUPropietarioRequest.setCodigoCliente(codigoCliente);
		consultarSKUPropietarioRequest.setFechaEncuesta(fechaEncuesta);
		String request = JSONHelper.serializar(consultarSKUPropietarioRequest);
		return request;
	}

	@Override
	protected ConsultarSKUPropietarioResponse responseText(String json) {
		// TODO Auto-generated method stub
		ConsultarSKUPropietarioResponse response = JSONHelper.desSerializar(json,ConsultarSKUPropietarioResponse.class);
		return response;
	}

}
