package pe.lindley.mmil.ws.service;

import pe.lindley.util.ProxyBase;
import pe.lindley.mmil.ws.bean.ConfigurarServicioRequest;
import pe.lindley.mmil.ws.bean.ConfigurarServicioResponse;
import pe.lindley.util.JSONHelper;
import roboguice.inject.InjectResource;

public class ConfigurarServicioProxy extends ProxyBase<ConfigurarServicioResponse> {
	
	@InjectResource(pe.lindley.activity.R.string.urlwsMMILConfig)protected String urlWS;

	private String modulo;

	public String getModulo() {
		return modulo;
	}

	public void setModulo(String modulo) {
		this.modulo = modulo;
	}

	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/getConfiguracion";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		ConfigurarServicioRequest configurarServicioRequest = new ConfigurarServicioRequest();
		configurarServicioRequest.setModulo(modulo);
		String request = JSONHelper.serializar(configurarServicioRequest);
		return request;
	}

	@Override
	protected ConfigurarServicioResponse responseText(String json) {
		// TODO Auto-generated method stub
		ConfigurarServicioResponse configurarServicioResponse  = JSONHelper.desSerializar(json,ConfigurarServicioResponse.class);
		return configurarServicioResponse;
	}
	

}
