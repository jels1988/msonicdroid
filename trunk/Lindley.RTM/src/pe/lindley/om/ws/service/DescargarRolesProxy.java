package pe.lindley.om.ws.service;


import pe.lindley.om.ws.bean.DescargarRolesRequest;
import pe.lindley.om.ws.bean.DescargarRolesResponse;
import pe.lindley.util.JSONHelper;
import pe.lindley.util.ProxyBase;
import roboguice.inject.InjectResource;

public class DescargarRolesProxy extends ProxyBase<DescargarRolesResponse> {
	
	@InjectResource(pe.lindley.activity.R.string.urlwsOMSeguridad)protected String urlWS;
	
	
	private String tipoRol;
	private String codigoDeposito;
	
	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/ListarRoles";
	}
	
	public String getTipoRol() {
		return tipoRol;
	}

	public void setTipoRol(String tipoRol) {
		this.tipoRol = tipoRol;
	}

	public String getCodigoDeposito() {
		return codigoDeposito;
	}

	public void setCodigoDeposito(String codigoDeposito) {
		this.codigoDeposito = codigoDeposito;
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		DescargarRolesRequest descargarRolesRequest = new DescargarRolesRequest();
		descargarRolesRequest.setCodigoDeposito(codigoDeposito);
		descargarRolesRequest.setTipoRol(tipoRol);
		
		String request = JSONHelper.serializar(descargarRolesRequest);
		return request;
		
	}
	@Override
	protected DescargarRolesResponse responseText(String json) {
		// TODO Auto-generated method stub
		DescargarRolesResponse descargarRolesResponse = JSONHelper.desSerializar(json,DescargarRolesResponse.class);
		return descargarRolesResponse;
	}
	
}
