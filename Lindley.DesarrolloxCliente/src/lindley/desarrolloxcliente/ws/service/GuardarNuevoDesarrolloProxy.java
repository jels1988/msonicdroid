package lindley.desarrolloxcliente.ws.service;

import java.util.List;

import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.to.GuardarOportunidadTO;
import lindley.desarrolloxcliente.to.SKUPresentacionTO;
import lindley.desarrolloxcliente.ws.bean.GuardarDesarrolloResponse;
import lindley.desarrolloxcliente.ws.bean.GuardarNuevoDesarrolloRequest;
import net.msonic.lib.JSONHelper;
import net.msonic.lib.ProxyBase;
import roboguice.inject.InjectResource;

public class GuardarNuevoDesarrolloProxy extends ProxyBase<GuardarDesarrolloResponse>{
	
	@InjectResource(R.string.urlwsDesarrolloxCliente)protected String urlWS;
		
	public List<GuardarOportunidadTO> oportunidadSistema;
		
	public List<SKUPresentacionTO> listSKUPresentacion;
	
	public String activosLindley;
	
	public String codigoCliente;

	public String codigoUsuario;
	
	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/GuardarNuevoCompromiso";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		GuardarNuevoDesarrolloRequest guardarDesarrolloRequest = new GuardarNuevoDesarrolloRequest();
		guardarDesarrolloRequest.oportunidadSistema = this.oportunidadSistema;
		guardarDesarrolloRequest.listSKUPresentacion = this.listSKUPresentacion;
		guardarDesarrolloRequest.activosLindley = this.activosLindley;
		guardarDesarrolloRequest.codigoCliente = this.codigoCliente;
		guardarDesarrolloRequest.codigoUsuario = this.codigoUsuario;
				
		String request = JSONHelper.serializar(guardarDesarrolloRequest);
		return request;
	}

	@Override
	protected GuardarDesarrolloResponse responseText(String json) {
		// TODO Auto-generated method stub
		GuardarDesarrolloResponse response = JSONHelper.desSerializar(json, GuardarDesarrolloResponse.class);
		return response;
	}

}
