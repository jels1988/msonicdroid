package pe.lindley.ventacero.ws.service;

import pe.lindley.util.JSONHelper;
import pe.lindley.util.ProxyBase;
import pe.lindley.ventacero.ws.bean.GuardarVtaCeroMotivoResponse;
import pe.lindley.ventacero.ws.bean.GuardarVtaCeroMotivoRequest;
import roboguice.inject.InjectResource;

public class GuardarVtaCeroMotivoProxy extends ProxyBase<GuardarVtaCeroMotivoResponse>{
	
	@InjectResource(pe.lindley.activity.R.string.urlwsVtaCero)protected String urlWS;
	
	private String Anio;
	private String Mes; 
	private String Semana;
	private String codDeposito;
	private String codCliente;
	private String Motivo;
	private String observacion;
	private String Usuario;

	public String getAnio() {
		return Anio;
	}

	public void setAnio(String anio) {
		Anio = anio;
	}

	public String getMes() {
		return Mes;
	}

	public void setMes(String mes) {
		Mes = mes;
	}

	public String getSemana() {
		return Semana;
	}

	public void setSemana(String semana) {
		Semana = semana;
	}

	public String getCodDeposito() {
		return codDeposito;
	}

	public void setCodDeposito(String codDeposito) {
		this.codDeposito = codDeposito;
	}

	public String getCodCliente() {
		return codCliente;
	}

	public void setCodCliente(String codCliente) {
		this.codCliente = codCliente;
	}

	public String getMotivo() {
		return Motivo;
	}

	public void setMotivo(String motivo) {
		Motivo = motivo;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public String getUsuario() {
		return Usuario;
	}

	public void setUsuario(String usuario) {
		Usuario = usuario;
	}

	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/GuardarMotivo";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		GuardarVtaCeroMotivoRequest guardarVtaCeroMotivoRequest = new GuardarVtaCeroMotivoRequest();
		guardarVtaCeroMotivoRequest.setAnio(Anio);
		guardarVtaCeroMotivoRequest.setCodCliente(codCliente);
		guardarVtaCeroMotivoRequest.setCodDeposito(codDeposito);
		guardarVtaCeroMotivoRequest.setMes(Mes);
		guardarVtaCeroMotivoRequest.setMotivo(Motivo);
		guardarVtaCeroMotivoRequest.setObservacion(observacion);
		guardarVtaCeroMotivoRequest.setSemana(Semana);
		guardarVtaCeroMotivoRequest.setUsuario(Usuario);
		String request = JSONHelper.serializar(guardarVtaCeroMotivoRequest);
		return request;
	}

	@Override
	protected GuardarVtaCeroMotivoResponse responseText(String json) {
		// TODO Auto-generated method stub
		GuardarVtaCeroMotivoResponse guardarVtaCeroMotivoResponse = JSONHelper.desSerializar(json, GuardarVtaCeroMotivoResponse.class);
		return guardarVtaCeroMotivoResponse;
	}

}
