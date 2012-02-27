package pe.lindley.plandesarrollo.ws.service;

import pe.lindley.plandesarrollo.ws.bean.GuardarActividadRequest;
import pe.lindley.plandesarrollo.ws.bean.GuardarActividadResponse;
import pe.lindley.util.JSONHelper;
import pe.lindley.util.ProxyBase;
import roboguice.inject.InjectResource;

public class GuardarActividadProxy extends ProxyBase<GuardarActividadResponse> {

	@InjectResource(pe.lindley.activity.R.string.urlwsPDActividad)protected String urlWS;
	
	private String codigoCliente;
	private String codigoPLan;
	private String descripcionActividad;
	private String fechTentativa;
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
	
	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/GuardarActividad";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		GuardarActividadRequest guardarActividadRequest = new GuardarActividadRequest();
		guardarActividadRequest.setCodigoCliente(codigoCliente);
		guardarActividadRequest.setCodigoPLan(codigoPLan);
		guardarActividadRequest.setDescripcionActividad(descripcionActividad);
		guardarActividadRequest.setFechTentativa(fechTentativa);		
		guardarActividadRequest.setUsuario(usuario);
		String request = JSONHelper.serializar(guardarActividadRequest);
		return request;
	}

	@Override
	protected GuardarActividadResponse responseText(String json) {
		// TODO Auto-generated method stub
		GuardarActividadResponse guardarActividadResponse = new GuardarActividadResponse();		
		return guardarActividadResponse;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

}
