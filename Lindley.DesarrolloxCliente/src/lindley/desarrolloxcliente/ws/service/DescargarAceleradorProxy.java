package lindley.desarrolloxcliente.ws.service;

import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.ws.bean.DescargarAceleradorResponse;
import net.msonic.lib.JSONHelper;
import net.msonic.lib.ProxyBase;
import roboguice.inject.InjectResource;

public class DescargarAceleradorProxy  extends ProxyBase<DescargarAceleradorResponse> {


	@InjectResource(R.string.urlwsDescargaService)protected String urlWS;
	
	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/downAceladores";
	}


	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		return "";
	}


	@Override
	protected DescargarAceleradorResponse responseText(String json) {
		// TODO Auto-generated method stub
		DescargarAceleradorResponse response = JSONHelper.desSerializar(json,DescargarAceleradorResponse.class);
		return response;
	}

}
