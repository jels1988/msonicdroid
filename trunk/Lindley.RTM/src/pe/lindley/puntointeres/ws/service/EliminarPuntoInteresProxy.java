package pe.lindley.puntointeres.ws.service;

import pe.lindley.puntointeres.ws.bean.EliminarPuntoInteresRequest;
import pe.lindley.puntointeres.ws.bean.EliminarPuntoInteresResponse;
import pe.lindley.util.JSONHelper;
import pe.lindley.util.ProxyBase;
import roboguice.inject.InjectResource;

public class EliminarPuntoInteresProxy extends ProxyBase<EliminarPuntoInteresResponse> {

	@InjectResource(pe.lindley.activity.R.string.urlwsPINT)protected String urlWS;
	
	private String codCliente;
	private String codPunto;
	private String usuario;

	public String getCodCliente() {
		return codCliente;
	}

	public void setCodCliente(String codCliente) {
		this.codCliente = codCliente;
	}

	public String getCodPunto() {
		return codPunto;
	}

	public void setCodPunto(String codPunto) {
		this.codPunto = codPunto;
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
		return urlWS + "/EliminarPtoInteres";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		EliminarPuntoInteresRequest eliminarPuntoInteresRequest = new EliminarPuntoInteresRequest();
		eliminarPuntoInteresRequest.setCodCliente(this.codCliente);
		eliminarPuntoInteresRequest.setCodPunto(this.codPunto);
		eliminarPuntoInteresRequest.setUsuario(this.usuario);
		String request = JSONHelper.serializar(eliminarPuntoInteresRequest);
		return request;
	}

	@Override
	protected EliminarPuntoInteresResponse responseText(String json) {
		// TODO Auto-generated method stub
		EliminarPuntoInteresResponse response = JSONHelper.desSerializar(json, EliminarPuntoInteresResponse.class);
		return response;
	}

}
