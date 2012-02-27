package pe.lindley.plandesarrollo.ws.service;

import pe.lindley.plandesarrollo.ws.bean.EliminarResponsableRequest;
import pe.lindley.plandesarrollo.ws.bean.EliminarResponsableResponse;
import pe.lindley.util.JSONHelper;
import pe.lindley.util.ProxyBase;
import roboguice.inject.InjectResource;

public class EliminarResponsableProxy extends ProxyBase<EliminarResponsableResponse> {

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
		return urlWS + "/EliminarResponsable";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		EliminarResponsableRequest eliminarResponsableRequest = new EliminarResponsableRequest();
		eliminarResponsableRequest.setCodigoActvidad(codigoActvidad);
		eliminarResponsableRequest.setCodigoCliente(codigoCliente);
		eliminarResponsableRequest.setCodigoPLan(codigoPLan);
		eliminarResponsableRequest.setCodigoResponsable(codigoResponsable);
		String request = JSONHelper.serializar(eliminarResponsableRequest);
		return request;
	}

	@Override
	protected EliminarResponsableResponse responseText(String json) {
		// TODO Auto-generated method stub
		EliminarResponsableResponse eliminarResponsableResponse = JSONHelper.desSerializar(json, EliminarResponsableResponse.class);
		return eliminarResponsableResponse;		
	}

}
