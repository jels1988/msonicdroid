package pe.lindley.ficha.ws.service;

import pe.lindley.ficha.ws.bean.ActualizarClienteResponse;
import pe.lindley.ficha.ws.bean.ActualizarClienteRequest;
import pe.lindley.util.ProxyBase;
import pe.lindley.util.JSONHelper;
import roboguice.inject.InjectResource;

public class ActualizarClienteProxy extends ProxyBase<ActualizarClienteResponse>{
	
	@InjectResource(pe.lindley.activity.R.string.urlwsFichaCliente)protected String urlWS;

	private String codigo;
	private String razonComercial;
	private String referencia;
	private String fechAniv;
	private String observaciones;
	private String usuario;

	
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getRazonComercial() {
		return razonComercial;
	}

	public void setRazonComercial(String razonComercial) {
		this.razonComercial = razonComercial;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getFechAniv() {
		return fechAniv;
	}

	public void setFechAniv(String fechAniv) {
		this.fechAniv = fechAniv;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
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
		return urlWS + "/actualizarCliente";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		ActualizarClienteRequest actualizarClienteRequest = new ActualizarClienteRequest();
		actualizarClienteRequest.setCliente(codigo);
		actualizarClienteRequest.setFechaAniversario(fechAniv);
		actualizarClienteRequest.setObservaciones(observaciones);
		actualizarClienteRequest.setRazonComercial(razonComercial);
		actualizarClienteRequest.setReferencia(referencia);
		actualizarClienteRequest.setUsuario(usuario);
		
		String request = JSONHelper.serializar(actualizarClienteRequest);
		return request;
	}

	@Override
	protected ActualizarClienteResponse responseText(String json) {
		// TODO Auto-generated method stub
		ActualizarClienteResponse actualizarClienteResponse = JSONHelper.desSerializar(json, ActualizarClienteResponse.class);
		return actualizarClienteResponse;
	}

}
