package pe.lindley.mmil.ws.service;

import pe.lindley.util.JSONHelper;
import pe.lindley.util.ProxyBase;
import pe.lindley.mmil.ws.bean.ListarMapVendedorRequest;
import pe.lindley.mmil.ws.bean.ListarMapVendedorResponse;
import roboguice.inject.InjectResource;

public class ListarMapVendedorProxy extends ProxyBase<ListarMapVendedorResponse>{
	
	@InjectResource(pe.lindley.activity.R.string.urlwsMMILMapVendedor)protected String urlWS;
	
	private String codigoDeposito;
	//private String fecha;
	private int codigoVendedor;

	public String getCodigoDeposito() {
		return codigoDeposito;
	}

	public void setCodigoDeposito(String codigoDeposito) {
		this.codigoDeposito = codigoDeposito;
	}

	/*public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}*/

	public int getCodigoVendedor() {
		return codigoVendedor;
	}

	public void setCodigoVendedor(int codigoVendedor) {
		this.codigoVendedor = codigoVendedor;
	}
	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/ListarMapVendedor";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		ListarMapVendedorRequest listarMapVendedorRequest = new ListarMapVendedorRequest();
		listarMapVendedorRequest.setCodigoDeposito(codigoDeposito);
		listarMapVendedorRequest.setCodigoVendedor(codigoVendedor);
		//listarMapVendedorRequest.setFecha(fecha);
		String request = JSONHelper.serializar(listarMapVendedorRequest);
		return request;
	}

	@Override
	protected ListarMapVendedorResponse responseText(String json) {
		// TODO Auto-generated method stub
		ListarMapVendedorResponse listarMapVendedorResponse = JSONHelper.desSerializar(json, ListarMapVendedorResponse.class);
		return listarMapVendedorResponse;
	}

}
