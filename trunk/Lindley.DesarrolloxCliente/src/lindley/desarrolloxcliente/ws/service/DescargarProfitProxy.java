package lindley.desarrolloxcliente.ws.service;

import roboguice.inject.InjectResource;

import com.google.inject.Inject;

import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.to.PeriodoTO;
import lindley.desarrolloxcliente.ws.bean.DescargaRequest;
import lindley.desarrolloxcliente.ws.bean.DescargarProfitResponse;
import net.msonic.lib.JSONHelper;
import net.msonic.lib.ProxyBase;

public class DescargarProfitProxy extends ProxyBase<DescargarProfitResponse> {

	@InjectResource(R.string.urlwsDescargaService)protected String urlWS;
	@Inject protected PeriodoTO periodoTO;
	

	
	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/downProfit";
	}



	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		DescargaRequest descargaRequest = new DescargaRequest();
		descargaRequest.anio=periodoTO.anio;
		descargaRequest.mes=periodoTO.mes;
		descargaRequest.deposito=periodoTO.deposito;
		descargaRequest.ruta=periodoTO.ruta;
		

		String request = JSONHelper.serializar(descargaRequest);
		return request;
	}

	@Override
	protected DescargarProfitResponse responseText(String json) {
		// TODO Auto-generated method stub
		DescargarProfitResponse response = JSONHelper.desSerializar(json, DescargarProfitResponse.class);
		return response;
	}

}
