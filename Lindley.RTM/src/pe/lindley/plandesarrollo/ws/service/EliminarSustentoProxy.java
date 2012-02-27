package pe.lindley.plandesarrollo.ws.service;

import pe.lindley.plandesarrollo.ws.bean.EliminarSustentoRequest;
import pe.lindley.plandesarrollo.ws.bean.EliminarSustentoResponse;
import pe.lindley.util.JSONHelper;
import pe.lindley.util.ProxyBase;
import roboguice.inject.InjectResource;

public class EliminarSustentoProxy extends ProxyBase<EliminarSustentoResponse> {

	@InjectResource(pe.lindley.activity.R.string.urlwsPDSustento) protected String urlWS;
	
	private String codigoSustento;
	private String codigoCliente;
	private String codigoPLan;
	private String codigoActvidad;

	public String getCodigoSustento() {
		return codigoSustento;
	}

	public void setCodigoSustento(String codigoSustento) {
		this.codigoSustento = codigoSustento;
	}

	public String getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public String getCodigoPLan() {
		return codigoPLan;
	}

	public void setCodigoPLan(String codigoPLan) {
		this.codigoPLan = codigoPLan;
	}

	public String getCodigoActvidad() {
		return codigoActvidad;
	}

	public void setCodigoActvidad(String codigoActvidad) {
		this.codigoActvidad = codigoActvidad;
	}

	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/EliminarSustento";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		EliminarSustentoRequest eliminarSustentoRequest = new EliminarSustentoRequest();
		eliminarSustentoRequest.setCodigoActvidad(codigoActvidad);
		eliminarSustentoRequest.setCodigoCliente(codigoCliente);
		eliminarSustentoRequest.setCodigoPLan(codigoPLan);
		eliminarSustentoRequest.setCodigoSustento(codigoSustento);
		String request = JSONHelper.serializar(eliminarSustentoRequest);
		return request;
	}

	@Override
	protected EliminarSustentoResponse responseText(String json) {
		// TODO Auto-generated method stub
		EliminarSustentoResponse eliminarSustentoResponse = JSONHelper.desSerializar(json, EliminarSustentoResponse.class);
		return eliminarSustentoResponse;
	}

}
