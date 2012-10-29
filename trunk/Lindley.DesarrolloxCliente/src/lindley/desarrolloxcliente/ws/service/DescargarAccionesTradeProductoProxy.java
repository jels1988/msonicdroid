package lindley.desarrolloxcliente.ws.service;

import roboguice.inject.InjectResource;

import com.google.inject.Inject;

import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.to.PeriodoTO;
import lindley.desarrolloxcliente.ws.bean.DescargarAccionTradeProductoResponse;
import net.msonic.lib.JSONHelper;
import net.msonic.lib.ProxyBase;

public class DescargarAccionesTradeProductoProxy extends ProxyBase<DescargarAccionTradeProductoResponse> {
	@InjectResource(R.string.urlwsDescargaService)protected String urlWS;
	@Inject protected PeriodoTO periodoTO;
	

	
	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/downAccTradeProd";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	protected DescargarAccionTradeProductoResponse responseText(String json) {
		// TODO Auto-generated method stub
		DescargarAccionTradeProductoResponse response = JSONHelper.desSerializar(json, DescargarAccionTradeProductoResponse.class);
		return response;
	}

}
