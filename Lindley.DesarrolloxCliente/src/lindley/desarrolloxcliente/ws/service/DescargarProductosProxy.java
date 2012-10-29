package lindley.desarrolloxcliente.ws.service;

import com.google.inject.Inject;

import roboguice.inject.InjectResource;
import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.to.PeriodoTO;
import lindley.desarrolloxcliente.ws.bean.DescargarProductosResponse;
import lindley.desarrolloxcliente.ws.bean.DescargaRequest;
import net.msonic.lib.JSONHelper;
import net.msonic.lib.ProxyBase;

public class DescargarProductosProxy extends ProxyBase<DescargarProductosResponse>{
	
	@InjectResource(R.string.urlwsDescargaService)protected String urlWS;
	@Inject protected PeriodoTO periodoTO;
	

	
	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/downProductos";
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
	protected DescargarProductosResponse responseText(String json) {
		// TODO Auto-generated method stub
		DescargarProductosResponse response = JSONHelper.desSerializar(json, DescargarProductosResponse.class);
		return response;
	}

}
