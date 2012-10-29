package lindley.desarrolloxcliente.ws.service;

import roboguice.inject.InjectResource;

import com.google.inject.Inject;

import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.to.PeriodoTO;
import lindley.desarrolloxcliente.ws.bean.DescargarAccionTradeResponse;
import net.msonic.lib.JSONHelper;
import net.msonic.lib.ProxyBase;

public class DescargarAccionesTradeProxy extends ProxyBase<DescargarAccionTradeResponse> {

	
	@InjectResource(R.string.urlwsDescargaService)protected String urlWS;
	@Inject protected PeriodoTO periodoTO;
	

	
	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/downAccTrade";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	protected DescargarAccionTradeResponse responseText(String json) {
		// TODO Auto-generated method stub
		DescargarAccionTradeResponse response = JSONHelper.desSerializar(json, DescargarAccionTradeResponse.class);
		return response;
	}

}
