package pe.lindley.plandesarrollo.ws.service;

import pe.lindley.plandesarrollo.ws.bean.ActualizarPlanRequest;
import pe.lindley.plandesarrollo.ws.bean.ActualizarPlanResponse;
import pe.lindley.util.JSONHelper;
import pe.lindley.util.ProxyBase;
import roboguice.inject.InjectResource;

public class ActualizarPlanProxy extends ProxyBase<ActualizarPlanResponse> {
	
	@InjectResource(pe.lindley.activity.R.string.urlwsPDPrincipal)protected String urlWS;

	private String codigoCliente;
	private String codigoPLan;
	private String porcentajeAvance;
	private String descripcionPLan;
	private String descripcionObjetivo;
	private String fechaCreacion;
	private String fechaInicio;
	private String fechaFin;
	private String estado;
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

	public String getPorcentajeAvance() {
		return porcentajeAvance;
	}

	public void setPorcentajeAvance(String porcentajeAvance) {
		this.porcentajeAvance = porcentajeAvance;
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

	public String getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
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
		return urlWS + "/ActualizarPlanDesarrollo";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		ActualizarPlanRequest actualizarPlanRequest = new ActualizarPlanRequest();
		actualizarPlanRequest.setCodigoCliente(codigoCliente);
		actualizarPlanRequest.setCodigoPLan(codigoPLan);
		actualizarPlanRequest.setDescripcionObjetivo(descripcionObjetivo);
		actualizarPlanRequest.setDescripcionPLan(descripcionPLan);
		actualizarPlanRequest.setEstado(estado);
		actualizarPlanRequest.setFechaCreacion(fechaCreacion);
		actualizarPlanRequest.setFechaFin(fechaFin);
		actualizarPlanRequest.setFechaInicio(fechaInicio);
		actualizarPlanRequest.setPorcentajeAvance(porcentajeAvance);
		actualizarPlanRequest.setUsuario(usuario);
		String request = JSONHelper.serializar(actualizarPlanRequest);
		return request;
	}

	@Override
	protected ActualizarPlanResponse responseText(String json) {
		// TODO Auto-generated method stub
		ActualizarPlanResponse actualizarPlanResponse = JSONHelper.desSerializar(json, ActualizarPlanResponse.class); 
		return actualizarPlanResponse;
	}

}
