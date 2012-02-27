package pe.lindley.prospector.ws.service;


import pe.lindley.prospector.ws.bean.FichasRechazadasRequest;
import pe.lindley.prospector.ws.bean.FichasRechazadasResponse;
import pe.lindley.util.JSONHelper;
import pe.lindley.util.ProxyBase;
import roboguice.inject.InjectResource;

public class FichasRechazadasProxy extends ProxyBase<FichasRechazadasResponse> {
	@InjectResource(pe.lindley.activity.R.string.urlwsCliente)protected String urlWS;
	
	private String usuario;
	
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/FichasRechazadas";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		FichasRechazadasRequest fichasRechazadasRequest = new FichasRechazadasRequest();
		fichasRechazadasRequest.setUsuario(usuario);
		
		String request = JSONHelper.serializar(fichasRechazadasRequest);
		return request;
	}

	@Override
	protected FichasRechazadasResponse responseText(String json) {
		// TODO Auto-generated method stub
		FichasRechazadasResponse fichasRechazadasResponse = JSONHelper.desSerializar(json,FichasRechazadasResponse.class);
		return fichasRechazadasResponse;
	}

}
