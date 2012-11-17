package lindley.desarrolloxcliente.ws.service;

import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.ws.bean.DescargarMotivoResponse;
import net.msonic.lib.JSONHelper;
import net.msonic.lib.ProxyBase;
import roboguice.inject.InjectResource;

public class DescargarMotivoProxy extends ProxyBase<DescargarMotivoResponse> {


	@InjectResource(R.string.urlwsDescargaService)protected String urlWS;
	
	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/downMotivos";
	}


	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		return "";
	}


	@Override
	protected DescargarMotivoResponse responseText(String json) {
		// TODO Auto-generated method stub
		DescargarMotivoResponse response = JSONHelper.desSerializar(json,DescargarMotivoResponse.class);
		return response;
	}

}
