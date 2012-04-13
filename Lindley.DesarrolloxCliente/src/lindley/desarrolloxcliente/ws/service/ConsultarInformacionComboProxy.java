package lindley.desarrolloxcliente.ws.service;

import roboguice.inject.InjectResource;
import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.ws.bean.ConsultarInformacionComboRequest;
import lindley.desarrolloxcliente.ws.bean.ConsultarInformacionComboResponse;
import net.msonic.lib.JSONHelper;
import net.msonic.lib.ProxyBase;

public class ConsultarInformacionComboProxy extends ProxyBase<ConsultarInformacionComboResponse> {

	@InjectResource(R.string.urlwsDesarrolloxCliente)protected String urlWS;
	public String codigoRegistro;
	
	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/ConsultarInformacionCombos";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		ConsultarInformacionComboRequest consultarInformacionComboRequest = new ConsultarInformacionComboRequest();
		consultarInformacionComboRequest.codigoRegistro = this.codigoRegistro;
		
		String request = JSONHelper.serializar(consultarInformacionComboRequest);
		return request;
	}

	@Override
	protected ConsultarInformacionComboResponse responseText(String json) {
		// TODO Auto-generated method stub
		ConsultarInformacionComboResponse response = JSONHelper.desSerializar(json, ConsultarInformacionComboResponse.class);
		return response; 
	}

}
