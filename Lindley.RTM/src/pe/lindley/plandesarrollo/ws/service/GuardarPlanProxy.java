package pe.lindley.plandesarrollo.ws.service;

import pe.lindley.plandesarrollo.ws.bean.GuardarPlanRequest;
import pe.lindley.plandesarrollo.ws.bean.GuardarPlanResponse;
import pe.lindley.util.JSONHelper;
import pe.lindley.util.ProxyBase;
import roboguice.inject.InjectResource;

public class GuardarPlanProxy extends ProxyBase<GuardarPlanResponse> {
	
	@InjectResource(pe.lindley.activity.R.string.urlwsPDPrincipal)protected String urlWS;

	private String codigoCliente;
	private String descripcionPLan;
	private String descripcionObjetivo;
	private String fechaCreacion;
	private String fechaInicio;
	private String usuario;

	public String getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public String getDescripcionPLan() {
		return descripcionPLan;
	}

	public void setDescripcionPLan(String descripcionPLan) {
		this.descripcionPLan = descripcionPLan;
	}

	public String getDescripcionObjetivo() {
		return descripcionObjetivo;
	}

	public void setDescripcionObjetivo(String descripcionObjetivo) {
		this.descripcionObjetivo = descripcionObjetivo;
	}

	public String getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
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
		return urlWS + "/GuardarPlanDesarrollo";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		GuardarPlanRequest guardarPlanRequest = new GuardarPlanRequest();
		guardarPlanRequest.setCodigoCliente(codigoCliente);
		guardarPlanRequest.setDescripcionObjetivo(descripcionObjetivo);
		guardarPlanRequest.setDescripcionPLan(descripcionPLan);
		guardarPlanRequest.setFechaCreacion(fechaCreacion);
		guardarPlanRequest.setFechaInicio(fechaInicio);
		guardarPlanRequest.setUsuario(usuario);
		String request = JSONHelper.serializar(guardarPlanRequest);
		return request;
	}

	@Override
	protected GuardarPlanResponse responseText(String json) {
		// TODO Auto-generated method stub
		GuardarPlanResponse guardarPlanResponse = JSONHelper.desSerializar(json, GuardarPlanResponse.class); 
		return guardarPlanResponse;
	}

}
