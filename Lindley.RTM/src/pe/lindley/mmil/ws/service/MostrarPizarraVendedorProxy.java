package pe.lindley.mmil.ws.service;

import pe.lindley.util.JSONHelper;
import pe.lindley.util.ProxyBase;
import pe.lindley.mmil.ws.bean.MostrarPizarraVendedorRequest;
import pe.lindley.mmil.ws.bean.MostrarPizarraResponse;
import roboguice.inject.InjectResource;

public class MostrarPizarraVendedorProxy extends ProxyBase<MostrarPizarraResponse>{
	
	@InjectResource(pe.lindley.activity.R.string.urlwsMMILPizarra)protected String urlWS;
	
	private String codDep;
	private String Fecha;

	public String getCodDep() {
		return codDep;
	}

	public void setCodDep(String codDep) {
		this.codDep = codDep;
	}

	public String getFecha() {
		return Fecha;
	}

	public void setFecha(String fecha) {
		Fecha = fecha;
	}

	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/ListarPizarra";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		MostrarPizarraVendedorRequest mostrarPizarraVendedorRequest = new MostrarPizarraVendedorRequest();
		mostrarPizarraVendedorRequest.setCodDep(codDep);
		mostrarPizarraVendedorRequest.setFecha(Fecha);
		String request = JSONHelper.serializar(mostrarPizarraVendedorRequest);
		return request;
	}

	@Override
	protected MostrarPizarraResponse responseText(String json) {
		// TODO Auto-generated method stub
		MostrarPizarraResponse mostrarPizarraResponse = JSONHelper.desSerializar(json, MostrarPizarraResponse.class);
		return mostrarPizarraResponse;
	}

}
