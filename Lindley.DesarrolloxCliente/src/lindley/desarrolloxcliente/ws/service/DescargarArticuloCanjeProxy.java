package lindley.desarrolloxcliente.ws.service;

import roboguice.inject.InjectResource;
import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.ws.bean.DescargarArticuloCanjeResponse;
import net.msonic.lib.JSONHelper;
import net.msonic.lib.ProxyBase;

public class DescargarArticuloCanjeProxy extends ProxyBase<DescargarArticuloCanjeResponse>{


	@InjectResource(R.string.urlwsDescargaService)protected String urlWS;
	
	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/downArticuloCanje";
	}


	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	protected DescargarArticuloCanjeResponse responseText(String json) {
		// TODO Auto-generated method stub
		DescargarArticuloCanjeResponse response = JSONHelper.desSerializar(json, DescargarArticuloCanjeResponse.class);
		return response;
	}

}
