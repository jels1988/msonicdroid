package pe.lindley.ficha.ws.service;

import pe.lindley.ficha.ws.bean.ActualizarComercialResponse;
import pe.lindley.ficha.ws.bean.ActualizarComercialRequest;
import pe.lindley.util.JSONHelper;
import pe.lindley.util.ProxyBase;
import roboguice.inject.InjectResource;

public class ActualizarComercialProxy extends ProxyBase<ActualizarComercialResponse>{

	@InjectResource(pe.lindley.activity.R.string.urlwsFichaComercial)protected String urlWS;
	
	private String codigo;
	private String giroSecundario;
	private String usuario;

	public String getCliente() {
		return codigo;
	}

	public void setCliente(String cliente) {
		this.codigo = cliente;
	}

	public String getGiroSecundario() {
		return giroSecundario;
	}

	public void setGiroSecundario(String giroSecundario) {
		this.giroSecundario = giroSecundario;
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
		return urlWS + "/ActualizarComercial";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		ActualizarComercialRequest actualizarComercialRequest = new ActualizarComercialRequest();
		actualizarComercialRequest.setCodigo(codigo);
		actualizarComercialRequest.setGiroSecundario(giroSecundario);
		actualizarComercialRequest.setUsuario(usuario);
		String request = JSONHelper.serializar(actualizarComercialRequest);
		return request;
	}

	@Override
	protected ActualizarComercialResponse responseText(String json) {
		// TODO Auto-generated method stub
		ActualizarComercialResponse actualizarComercialResponse = JSONHelper.desSerializar(json, ActualizarComercialResponse.class);
		return actualizarComercialResponse;
	}
}
