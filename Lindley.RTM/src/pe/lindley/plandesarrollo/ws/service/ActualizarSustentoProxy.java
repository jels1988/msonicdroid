package pe.lindley.plandesarrollo.ws.service;

import pe.lindley.plandesarrollo.ws.bean.ActualizarSustentoRequest;
import pe.lindley.plandesarrollo.ws.bean.ActualizarSustentoResponse;
import pe.lindley.util.JSONHelper;
import pe.lindley.util.ProxyBase;
import roboguice.inject.InjectResource;

public class ActualizarSustentoProxy extends ProxyBase<ActualizarSustentoResponse> {

	@InjectResource(pe.lindley.activity.R.string.urlwsPDSustento) protected String urlWS;
	
	private String codigoSustento;
	private String codigoCliente;
	private String codigoPLan;
	private String codigoActvidad;
	private String fechaVisita;
	private String tipoActividad;
	private String descripcionActividad;
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

	public String getFechaVisita() {
		return fechaVisita;
	}

	public void setFechaVisita(String fechaVisita) {
		this.fechaVisita = fechaVisita;
	}

	public String getTipoActividad() {
		return tipoActividad;
	}

	public void setTipoActividad(String tipoActividad) {
		this.tipoActividad = tipoActividad;
	}

	public String getDescripcionActividad() {
		return descripcionActividad;
	}

	public void setDescripcionActividad(String descripcionActividad) {
		this.descripcionActividad = descripcionActividad;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getCodigoSustento() {
		return codigoSustento;
	}

	public void setCodigoSustento(String codigoSustento) {
		this.codigoSustento = codigoSustento;
	}
	
	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/ActualizarSustento";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		ActualizarSustentoRequest actualizarSustentoRequest = new ActualizarSustentoRequest();
		actualizarSustentoRequest.setCodigoActvidad(codigoActvidad);
		actualizarSustentoRequest.setCodigoCliente(codigoCliente);
		actualizarSustentoRequest.setCodigoPLan(codigoPLan);
		actualizarSustentoRequest.setCodigoSustento(codigoSustento);
		actualizarSustentoRequest.setDescripcionActividad(descripcionActividad);
		actualizarSustentoRequest.setFechaVisita(fechaVisita);
		actualizarSustentoRequest.setTipoActividad(tipoActividad);
		actualizarSustentoRequest.setUsuario(usuario);
		String request = JSONHelper.serializar(actualizarSustentoRequest);
		return request;
	}

	@Override
	protected ActualizarSustentoResponse responseText(String json) {
		// TODO Auto-generated method stub
		ActualizarSustentoResponse actualizarSustentoResponse = JSONHelper.desSerializar(json, ActualizarSustentoResponse.class);
		return actualizarSustentoResponse;
	}

}
