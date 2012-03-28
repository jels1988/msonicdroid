package lindley.desarrolloxcliente.ws.service;
import roboguice.inject.InjectResource;
import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.ws.bean.ConsultarProfitRequest;
import lindley.desarrolloxcliente.ws.bean.ConsultarProfitResponse;
import net.msonic.lib.JSONHelper;
import net.msonic.lib.ProxyBase;

public class ConsultarProfitProxy extends ProxyBase<ConsultarProfitResponse> {

	@InjectResource(R.string.urlwsDesarrolloxCliente)protected String urlWS;
	
	public String anio;
	public String mes;
	public String codigoCliente;
	public String codigoArticulo;
	
	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/ConsultarProfit";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		ConsultarProfitRequest consultarProfitRequest = new ConsultarProfitRequest();
		consultarProfitRequest.anio = this.anio;
		consultarProfitRequest.codigoArticulo = this.codigoArticulo;
		consultarProfitRequest.codigoCliente = this.codigoCliente;
		consultarProfitRequest.mes = this.mes;
		
		String request = JSONHelper.serializar(consultarProfitRequest);
		return request;
	}

	@Override
	protected ConsultarProfitResponse responseText(String json) {
		// TODO Auto-generated method stub
		ConsultarProfitResponse response = JSONHelper.desSerializar(json, ConsultarProfitResponse.class);
		return response;
	}

}
