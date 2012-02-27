package pe.lindley.mmil.ws.service;

import pe.lindley.util.JSONHelper;
import pe.lindley.util.ProxyBase;
import pe.lindley.mmil.ws.bean.MostrarPizarraCdaRequest;
import pe.lindley.mmil.ws.bean.MostrarPizarraResponse;
import roboguice.inject.InjectResource;

public class MostrarPizarraCdaProxy extends ProxyBase<MostrarPizarraResponse>{

	@InjectResource(pe.lindley.activity.R.string.urlwsMMILPizarra)protected String urlWS;
	
/*	private String fecha;

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}*/	

	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/ListarPizarraCDA";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		MostrarPizarraCdaRequest mostrarPizarraCdaRequest = new MostrarPizarraCdaRequest();
		//mostrarPizarraCdaRequest.setFecha(fecha);
		String request = JSONHelper.serializar(mostrarPizarraCdaRequest);
		return request;
	}

	@Override
	protected MostrarPizarraResponse responseText(String json) {
		// TODO Auto-generated method stub
		MostrarPizarraResponse mostrarPizarraResponse = JSONHelper.desSerializar(json, MostrarPizarraResponse.class);
		return mostrarPizarraResponse;
	}
	
}
