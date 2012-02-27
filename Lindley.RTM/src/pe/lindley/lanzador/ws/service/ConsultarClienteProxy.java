package pe.lindley.lanzador.ws.service;

import pe.lindley.lanzador.ws.bean.ConsultarClienteRequest;
import pe.lindley.lanzador.ws.bean.ConsultarClienteResponse;
import pe.lindley.util.ProxyBase;
import roboguice.inject.InjectResource;

public class ConsultarClienteProxy extends ProxyBase<ConsultarClienteResponse> {
	
	@InjectResource(pe.lindley.activity.R.string.urlwsCliente)protected String urlWS;

	
	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		ConsultarClienteRequest consultarClienteRequest = new ConsultarClienteRequest();
		consultarClienteRequest.setCodigo(this.codigo);
		consultarClienteRequest.setDni(this.dni);
		consultarClienteRequest.setRuc(this.ruc);
		
		String request = pe.lindley.util.JSONHelper.serializar(consultarClienteRequest);
		return request;
		
	}

	@Override
	protected ConsultarClienteResponse responseText(String json) {
		// TODO Auto-generated method stub
		ConsultarClienteResponse response = pe.lindley.util.JSONHelper.desSerializar(json, ConsultarClienteResponse.class);
		return response;
	}
	
	private String codigo;
	private String ruc;
	private String dni;
	
	public String getCodigo() {
		return codigo;
	}




	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}




	public String getRuc() {
		return ruc;
	}




	public void setRuc(String ruc) {
		this.ruc = ruc;
	}




	public String getDni() {
		return dni;
	}




	public void setDni(String dni) {
		this.dni = dni;
	}

	@Override
	protected String getUrl() {
		return urlWS + "/ConsultarCliente";
	}
}
