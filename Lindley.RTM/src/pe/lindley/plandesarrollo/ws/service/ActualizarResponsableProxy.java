package pe.lindley.plandesarrollo.ws.service;

import pe.lindley.plandesarrollo.ws.bean.ActualizarResponsableRequest;
import pe.lindley.plandesarrollo.ws.bean.ActualizarResponsableResponse;
import pe.lindley.util.JSONHelper;
import pe.lindley.util.ProxyBase;
import roboguice.inject.InjectResource;

public class ActualizarResponsableProxy extends ProxyBase<ActualizarResponsableResponse> {

	@InjectResource(pe.lindley.activity.R.string.urlwsPDResponsable)protected String urlWS;
	
	private String codigoCliente;
	private String codigoPLan;
	private String codigoActvidad;
	private String codigoResponsableAntiguo;
	private String codigoResponsableNuevo;
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

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	public String getCodigoResponsableAntiguo() {
		return codigoResponsableAntiguo;
	}

	public void setCodigoResponsableAntiguo(String codigoResponsableAntiguo) {
		this.codigoResponsableAntiguo = codigoResponsableAntiguo;
	}

	public String getCodigoResponsableNuevo() {
		return codigoResponsableNuevo;
	}

	public void setCodigoResponsableNuevo(String codigoResponsableNuevo) {
		this.codigoResponsableNuevo = codigoResponsableNuevo;
	}
	
	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/ActualizarResponsable";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		ActualizarResponsableRequest actualizarResponsableRequest = new ActualizarResponsableRequest();
		actualizarResponsableRequest.setCodigoActvidad(codigoActvidad);
		actualizarResponsableRequest.setCodigoCliente(codigoCliente);
		actualizarResponsableRequest.setCodigoPLan(codigoPLan);
		actualizarResponsableRequest.setCodigoResponsableAntiguo(codigoResponsableAntiguo);
		actualizarResponsableRequest.setCodigoResponsableNuevo(codigoResponsableNuevo);
		actualizarResponsableRequest.setUsuario(usuario);
		String request = JSONHelper.serializar(actualizarResponsableRequest);
		return request;
	}

	@Override
	protected ActualizarResponsableResponse responseText(String json) {
		// TODO Auto-generated method stub
		ActualizarResponsableResponse actualizarResponsableResponse = JSONHelper.desSerializar(json, ActualizarResponsableResponse.class);
		return actualizarResponsableResponse;
	}

}
