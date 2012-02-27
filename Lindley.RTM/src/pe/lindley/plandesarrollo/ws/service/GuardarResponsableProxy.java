package pe.lindley.plandesarrollo.ws.service;

import pe.lindley.plandesarrollo.ws.bean.GuardarResponsableRequest;
import pe.lindley.plandesarrollo.ws.bean.GuardarResponsableResponse;
import pe.lindley.util.JSONHelper;
import pe.lindley.util.ProxyBase;
import roboguice.inject.InjectResource;

public class GuardarResponsableProxy extends ProxyBase<GuardarResponsableResponse> {

	@InjectResource(pe.lindley.activity.R.string.urlwsPDResponsable)protected String urlWS;
	
	private String codigoCliente;
	private String codigoPLan;
	private String codigoActvidad;
	private String codigoResponsable;
	private String usuario;

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

	public String getCodigoResponsable() {
		return codigoResponsable;
	}

	public void setCodigoResponsable(String codigoResponsable) {
		this.codigoResponsable = codigoResponsable;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/GuardarResponsable";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		GuardarResponsableRequest guardarResponsableRequest = new GuardarResponsableRequest();
		guardarResponsableRequest.setCodigoActvidad(codigoActvidad);
		guardarResponsableRequest.setCodigoCliente(codigoCliente);
		guardarResponsableRequest.setCodigoPLan(codigoPLan);
		guardarResponsableRequest.setCodigoResponsable(codigoResponsable);
		guardarResponsableRequest.setUsuario(usuario);
		String request = JSONHelper.serializar(guardarResponsableRequest);
		return request;
	}

	@Override
	protected GuardarResponsableResponse responseText(String json) {
		// TODO Auto-generated method stub
		GuardarResponsableResponse guardarResponsableResponse = JSONHelper.desSerializar(json, GuardarResponsableResponse.class);		
		return guardarResponsableResponse;
	}

}
