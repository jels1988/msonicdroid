package lindley.desarrolloxcliente.ws.service;

import roboguice.inject.InjectResource;
import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.ws.bean.DescargarTipoActivoResponse;
import net.msonic.lib.JSONHelper;
import net.msonic.lib.ProxyBase;

public class DescargarTipoActivoProxy extends ProxyBase<DescargarTipoActivoResponse> {
	@InjectResource(R.string.urlwsDescargaService)protected String urlWS;
	
	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return  urlWS + "/downTipoActivo";
		
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		return  "";
	}

	@Override
	protected DescargarTipoActivoResponse responseText(String json) {
		// TODO Auto-generated method stub
		DescargarTipoActivoResponse response = JSONHelper.desSerializar(json, DescargarTipoActivoResponse.class);
		return response;
	}

}
