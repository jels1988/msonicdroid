package pe.lindley.mmil.ws.service;

import pe.lindley.util.JSONHelper;
import pe.lindley.util.ProxyBase;
import pe.lindley.mmil.ws.bean.ListarVendedorRequest;
import pe.lindley.mmil.ws.bean.ListarVendedorResponse;
import roboguice.inject.InjectResource;

public class ListarVendedorProxy extends ProxyBase<ListarVendedorResponse>{

	@InjectResource(pe.lindley.activity.R.string.urlwsMMILVendedor)protected String urlWS;
	
	private String codigoDeposito;
	private String tipo;
	private String codigoSupervisor;

	public String getCodigoDeposito() {
		return codigoDeposito;
	}

	public void setCodigoDeposito(String codigoDeposito) {
		this.codigoDeposito = codigoDeposito;
	}


	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getCodigoSupervisor() {
		return codigoSupervisor;
	}

	public void setCodigoSupervisor(String codigoSupervisor) {
		this.codigoSupervisor = codigoSupervisor;
	}


	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/ListarVendedor";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		ListarVendedorRequest listarVendedorRequest = new ListarVendedorRequest();
		listarVendedorRequest.setCodigoDeposito(codigoDeposito);
		listarVendedorRequest.setCodigoSupervisor(codigoSupervisor);
		listarVendedorRequest.setTipo(tipo);
		String request = JSONHelper.serializar(listarVendedorRequest);
		return request;
	}

	@Override
	protected ListarVendedorResponse responseText(String json) {
		// TODO Auto-generated method stub
		ListarVendedorResponse listarVendedorResponse = JSONHelper.desSerializar(json, ListarVendedorResponse.class);
		return listarVendedorResponse;
	}
}
