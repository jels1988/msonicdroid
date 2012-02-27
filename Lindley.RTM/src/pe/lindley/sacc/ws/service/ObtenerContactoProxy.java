package pe.lindley.sacc.ws.service;

import com.google.gson.annotations.SerializedName;

import pe.lindley.sacc.ws.bean.ObtenerContactoRequest;
import pe.lindley.sacc.ws.bean.ObtenerContactoResponse;
import pe.lindley.util.JSONHelper;
import pe.lindley.util.ProxyBase;
import roboguice.inject.InjectResource;

public class ObtenerContactoProxy extends ProxyBase<ObtenerContactoResponse> {
	
	@InjectResource(pe.lindley.activity.R.string.urlswsSACC)protected String urlWS;

	@SerializedName("CLI")
	private int idCliente;

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/ListarContactoCliente";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		ObtenerContactoRequest obtenerContactoRequest = new ObtenerContactoRequest();
		obtenerContactoRequest.setIdCliente(this.idCliente);
		String request = JSONHelper.serializar(obtenerContactoRequest);
		return request;
	}

	@Override
	protected ObtenerContactoResponse responseText(String json) {
		// TODO Auto-generated method stub
		ObtenerContactoResponse obtenerContactoResponse = JSONHelper.desSerializar(json, ObtenerContactoResponse.class);
		return obtenerContactoResponse;
	}
}
