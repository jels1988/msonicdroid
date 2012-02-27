package pe.lindley.plandesarrollo.ws.service;

import pe.lindley.plandesarrollo.ws.bean.ConsultarResponsableRequest;
import pe.lindley.plandesarrollo.ws.bean.ConsultarResponsableResponse;
import pe.lindley.util.JSONHelper;
import pe.lindley.util.ProxyBase;
import roboguice.inject.InjectResource;

public class ConsultarResponsableProxy extends ProxyBase<ConsultarResponsableResponse> {

	@InjectResource(pe.lindley.activity.R.string.urlwsPDResponsable)protected String urlWS;
	
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
		return urlWS + "/ListarResponsable";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		ConsultarResponsableRequest consultarResponsableRequest = new ConsultarResponsableRequest();
		consultarResponsableRequest.setCodigoActvidad(codigoActvidad);
		consultarResponsableRequest.setCodigoCliente(codigoCliente);
		consultarResponsableRequest.setCodigoPLan(codigoPLan);
		String request = JSONHelper.serializar(consultarResponsableRequest);
		return request;
	}

	@Override
	protected ConsultarResponsableResponse responseText(String json) {
		// TODO Auto-generated method stub
		ConsultarResponsableResponse consultarResponsableResponse = JSONHelper.desSerializar(json, ConsultarResponsableResponse.class);
		return consultarResponsableResponse;
	}

}
