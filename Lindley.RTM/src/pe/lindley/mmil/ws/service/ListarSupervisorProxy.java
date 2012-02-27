package pe.lindley.mmil.ws.service;

import pe.lindley.util.JSONHelper;
import pe.lindley.util.ProxyBase;
import pe.lindley.mmil.ws.bean.ListarSupervisorRequest;
import pe.lindley.mmil.ws.bean.ListarSupervisorResponse;
import roboguice.inject.InjectResource;

public class ListarSupervisorProxy extends ProxyBase<ListarSupervisorResponse>{

	@InjectResource(pe.lindley.activity.R.string.urlwsMMILSupervisor)protected String urlWS;
	
	private String codigoDeposito;
	private int tipo;

	public String getCodigoDeposito() {
		return codigoDeposito;
	}

	public void setCodigoDeposito(String codigoDeposito) {
		this.codigoDeposito = codigoDeposito;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/ListarSupervisor";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		ListarSupervisorRequest listarSupervisorRequest = new ListarSupervisorRequest();
		listarSupervisorRequest.setCodigoDeposito(codigoDeposito);
		listarSupervisorRequest.setTipo(tipo);
		String request = JSONHelper.serializar(listarSupervisorRequest);
		return request;
	}

	@Override
	protected ListarSupervisorResponse responseText(String json) {
		// TODO Auto-generated method stub
		ListarSupervisorResponse listarSupervisorResponse = JSONHelper.desSerializar(json, ListarSupervisorResponse.class);
		return listarSupervisorResponse;
	}
}
