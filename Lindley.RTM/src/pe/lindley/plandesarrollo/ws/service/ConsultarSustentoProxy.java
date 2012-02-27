package pe.lindley.plandesarrollo.ws.service;

import pe.lindley.plandesarrollo.ws.bean.ConsultarSustentoRequest;
import pe.lindley.plandesarrollo.ws.bean.ConsultarSustentoResponse;
import pe.lindley.util.JSONHelper;
import pe.lindley.util.ProxyBase;
import roboguice.inject.InjectResource;

public class ConsultarSustentoProxy extends ProxyBase<ConsultarSustentoResponse> {

	@InjectResource(pe.lindley.activity.R.string.urlwsPDSustento) protected String urlWS;
	
	private String codigoCliente;
	private String codigoPLan;
	private String codigoActvidad;
	
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
		return urlWS + "/ListarSustento";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		ConsultarSustentoRequest consultarSustentoRequest = new ConsultarSustentoRequest();
		consultarSustentoRequest.setCodigoActvidad(codigoActvidad);
		consultarSustentoRequest.setCodigoCliente(codigoCliente);
		consultarSustentoRequest.setCodigoPLan(codigoPLan);
		String request = JSONHelper.serializar(consultarSustentoRequest);
		return request;
	}

	@Override
	protected ConsultarSustentoResponse responseText(String json) {
		// TODO Auto-generated method stub
		ConsultarSustentoResponse consultarSustentoResponse = JSONHelper.desSerializar(json, ConsultarSustentoResponse.class);
		return consultarSustentoResponse;
	}

}
