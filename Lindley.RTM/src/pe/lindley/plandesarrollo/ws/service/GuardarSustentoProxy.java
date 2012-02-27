package pe.lindley.plandesarrollo.ws.service;

import pe.lindley.plandesarrollo.ws.bean.GuardarSustentoRequest;
import pe.lindley.plandesarrollo.ws.bean.GuardarSustentoResponse;
import pe.lindley.util.JSONHelper;
import pe.lindley.util.ProxyBase;
import roboguice.inject.InjectResource;

public class GuardarSustentoProxy extends ProxyBase<GuardarSustentoResponse>{

	@InjectResource(pe.lindley.activity.R.string.urlwsPDSustento) protected String urlWS;
	
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

	
	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/GuardarSustento";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		GuardarSustentoRequest guardarSustentoRequest = new GuardarSustentoRequest();
		guardarSustentoRequest.setCodigoActvidad(codigoActvidad);
		guardarSustentoRequest.setCodigoCliente(codigoCliente);
		guardarSustentoRequest.setCodigoPLan(codigoPLan);
		guardarSustentoRequest.setDescripcionActividad(descripcionActividad);
		guardarSustentoRequest.setFechaVisita(fechaVisita);
		guardarSustentoRequest.setTipoActividad(tipoActividad);
		guardarSustentoRequest.setUsuario(usuario);
		String request = JSONHelper.serializar(guardarSustentoRequest);
		return request;
	}

	@Override
	protected GuardarSustentoResponse responseText(String json) {
		// TODO Auto-generated method stub
		GuardarSustentoResponse guardarSustentoResponse = JSONHelper.desSerializar(json, GuardarSustentoResponse.class);
		return guardarSustentoResponse;
	}

}
