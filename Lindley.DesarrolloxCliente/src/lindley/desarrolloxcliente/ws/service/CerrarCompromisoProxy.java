package lindley.desarrolloxcliente.ws.service;

import java.util.List;

import roboguice.inject.InjectResource;
import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.to.CompromisoTO;
import lindley.desarrolloxcliente.to.UpdatePosicionTO;
import lindley.desarrolloxcliente.to.UpdatePresentacionTO;
import lindley.desarrolloxcliente.ws.bean.CerrarCompromisoRequest;
import lindley.desarrolloxcliente.ws.bean.CerrarCompromisoResponse;
import net.msonic.lib.JSONHelper;
import net.msonic.lib.ProxyBase;

public class CerrarCompromisoProxy extends ProxyBase<CerrarCompromisoResponse> {

	@InjectResource(R.string.urlwsDesarrolloxCliente)protected String urlWS;
	private String codigoCabecera;
	
	private List<CompromisoTO> compromisos;
	
	public List<UpdatePosicionTO> listaPosicionCompromiso;
	
	public List<UpdatePresentacionTO> listaPresentacionCompromiso;

	public List<CompromisoTO> getCompromisos() {
		return compromisos;
	}

	public void setCompromisos(List<CompromisoTO> compromisos) {
		this.compromisos = compromisos;
	}

	public String getCodigoCabecera() {
		return codigoCabecera;
	}

	public void setCodigoCabecera(String codigoCabecera) {
		this.codigoCabecera = codigoCabecera;
	}
	
	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/CerrarCompromiso";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		CerrarCompromisoRequest cerrarCompromisoRequest = new CerrarCompromisoRequest();
		cerrarCompromisoRequest.setCodigoCabecera(this.codigoCabecera);
		cerrarCompromisoRequest.setCompromisos(this.compromisos);
		cerrarCompromisoRequest.listaPosicionCompromiso = this.listaPosicionCompromiso;
		cerrarCompromisoRequest.listaPresentacionCompromiso = this.listaPresentacionCompromiso;
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
