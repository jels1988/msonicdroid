package lindley.desarrolloxcliente.ws.service;

import java.util.List;

import roboguice.inject.InjectResource;
import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.to.CerrarInventarioTO;
import lindley.desarrolloxcliente.to.CerrarPosicionTO;
import lindley.desarrolloxcliente.to.CerrarPresentacionTO;
import lindley.desarrolloxcliente.ws.bean.CerrarCompromisoRequest;
import lindley.desarrolloxcliente.ws.bean.CerrarCompromisoResponse;
import net.msonic.lib.JSONHelper;
import net.msonic.lib.ProxyBase;

public class CerrarCompromisoProxy extends ProxyBase<CerrarCompromisoResponse> {

	@InjectResource(R.string.urlwsDesarrolloxCliente)protected String urlWS;
	
	public String codigoUsuario;
	
	public String codigoCabecera;
	
	public List<CerrarInventarioTO> listaInventarioCompromiso;
	
	public List<CerrarPosicionTO> listaPosicionCompromiso;
	
	public List<CerrarPresentacionTO> listaPresentacionCompromiso;
	
	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/CerrarCompromiso";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		CerrarCompromisoRequest cerrarCompromisoRequest = new CerrarCompromisoRequest();
		cerrarCompromisoRequest.codigoCabecera = this.codigoCabecera;
		cerrarCompromisoRequest.listaInventarioCompromiso = this.listaInventarioCompromiso;
		cerrarCompromisoRequest.listaPosicionCompromiso = this.listaPosicionCompromiso;
		cerrarCompromisoRequest.listaPresentacionCompromiso = this.listaPresentacionCompromiso;
		cerrarCompromisoRequest.codigoUsuario = this.codigoUsuario;
		String request = JSONHelper.serializar(cerrarCompromisoRequest);
		return request;
	}

	@Override
	protected CerrarCompromisoResponse responseText(String json) {
		// TODO Auto-generated method stub
		CerrarCompromisoResponse response = JSONHelper.desSerializar(json, CerrarCompromisoResponse.class);
		return response;
	}

}
