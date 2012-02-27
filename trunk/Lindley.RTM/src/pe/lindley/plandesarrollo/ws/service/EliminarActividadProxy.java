package pe.lindley.plandesarrollo.ws.service;

import pe.lindley.plandesarrollo.ws.bean.EliminarActividadRequest;
import pe.lindley.plandesarrollo.ws.bean.EliminarActividadResponse;
import pe.lindley.util.JSONHelper;
import pe.lindley.util.ProxyBase;
import roboguice.inject.InjectResource;

public class EliminarActividadProxy extends ProxyBase<EliminarActividadResponse> {

	@InjectResource(pe.lindley.activity.R.string.urlwsPDActividad)protected String urlWS;	
	private String codigoActividad;

	public String getCodigoActividad() {
		return codigoActividad;
	}

	public void setCodigoActividad(String codigoActividad) {
		this.codigoActividad = codigoActividad;
	}

	
	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/EliminarActividad";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		EliminarActividadRequest eliminarActividadRequest = new EliminarActividadRequest();
		eliminarActividadRequest.setCodigoActividad(codigoActividad);
		String request = JSONHelper.serializar(eliminarActividadRequest);
		return request;
	}

	@Override
	protected EliminarActividadResponse responseText(String json) {
		// TODO Auto-generated method stub
		EliminarActividadResponse eliminarActividadResponse = JSONHelper.desSerializar(json, EliminarActividadResponse.class);
		return eliminarActividadResponse;
	}

}


