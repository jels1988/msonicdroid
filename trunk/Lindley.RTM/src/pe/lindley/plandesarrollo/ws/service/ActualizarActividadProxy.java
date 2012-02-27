package pe.lindley.plandesarrollo.ws.service;

import pe.lindley.plandesarrollo.ws.bean.ActualizarActividadRequest;
import pe.lindley.plandesarrollo.ws.bean.ActualizarActividadResponse;
import pe.lindley.util.JSONHelper;
import pe.lindley.util.ProxyBase;
import roboguice.inject.InjectResource;

public class ActualizarActividadProxy extends ProxyBase<ActualizarActividadResponse> {

	@InjectResource(pe.lindley.activity.R.string.urlwsPDActividad) protected String urlWS;
	
	private String codigoCliente;
	private String codigoPLan;
	private String estado;
	private String descripcionActividad;
	private String fechTentativa;
	private String usuario;
	private String codigoActividad;
	
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

	public String getDescripcionActividad() {
		return descripcionActividad;
	}

	public void setDescripcionActividad(String descripcionActividad) {
		this.descripcionActividad = descripcionActividad;
	}

	public String getFechTentativa() {
		return fechTentativa;
	}

	public void setFechTentativa(String fechTentativa) {
		this.fechTentativa = fechTentativa;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/ActualizarActividad";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		ActualizarActividadRequest actualizarActividadRequest = new ActualizarActividadRequest();
		actualizarActividadRequest.setCodigoCliente(codigoCliente);
		actualizarActividadRequest.setCodigoPLan(codigoPLan);
		actualizarActividadRequest.setCodigoActividad(codigoActividad);
		actualizarActividadRequest.setDescripcionActividad(descripcionActividad);
		actualizarActividadRequest.setEstado(estado);
		actualizarActividadRequest.setFechTentativa(fechTentativa);
		actualizarActividadRequest.setUsuario(usuario);
		String request = JSONHelper.serializar(actualizarActividadRequest);
		return request;
	}

	@Override
	protected ActualizarActividadResponse responseText(String json) {
		// TODO Auto-generated method stub
		ActualizarActividadResponse actualizarActividadResponse = JSONHelper.desSerializar(json, ActualizarActividadResponse.class); 
		return actualizarActividadResponse;
	}

	public String getCodigoActividad() {
		return codigoActividad;
	}

	public void setCodigoActividad(String codigoActividad) {
		this.codigoActividad = codigoActividad;
	}

}
