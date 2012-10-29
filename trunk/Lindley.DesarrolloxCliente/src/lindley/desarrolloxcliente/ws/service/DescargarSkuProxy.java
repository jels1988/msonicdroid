package lindley.desarrolloxcliente.ws.service;

import roboguice.inject.InjectResource;

import com.google.inject.Inject;

import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.to.PeriodoTO;
import lindley.desarrolloxcliente.ws.bean.DescargarSkuResponse;
import net.msonic.lib.JSONHelper;
import net.msonic.lib.ProxyBase;

public class DescargarSkuProxy extends ProxyBase<DescargarSkuResponse> {

	@InjectResource(R.string.urlwsDescargaService)protected String urlWS;
	@Inject protected PeriodoTO periodoTO;
	

	
	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/downSKU";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	protected DescargarSkuResponse responseText(String json) {
		// TODO Auto-generated method stub
		DescargarSkuResponse response = JSONHelper.desSerializar(json, DescargarSkuResponse.class);
		return response;
	}

}
