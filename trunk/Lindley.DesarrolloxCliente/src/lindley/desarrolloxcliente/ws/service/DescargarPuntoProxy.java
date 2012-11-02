package lindley.desarrolloxcliente.ws.service;

import roboguice.inject.InjectResource;


import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.ws.bean.DescargarPuntoResponse;
import net.msonic.lib.JSONHelper;
import net.msonic.lib.ProxyBase;

public class DescargarPuntoProxy extends ProxyBase<DescargarPuntoResponse> {


	@InjectResource(R.string.urlwsDescargaService)protected String urlWS;
	
	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/downPunto";
	}


	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	protected DescargarPuntoResponse responseText(String json) {
		// TODO Auto-generated method stub
		DescargarPuntoResponse response = JSONHelper.desSerializar(json,DescargarPuntoResponse.class);
		return response;
	}

}
