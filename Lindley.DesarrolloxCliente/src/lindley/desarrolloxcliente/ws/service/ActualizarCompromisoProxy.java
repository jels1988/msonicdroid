package lindley.desarrolloxcliente.ws.service;

import java.util.List;
import roboguice.inject.InjectResource;
import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.to.CompromisoTO;
import lindley.desarrolloxcliente.to.UpdatePosicionTO;
import lindley.desarrolloxcliente.to.UpdatePresentacionTO;
import lindley.desarrolloxcliente.ws.bean.ActualizarCompromisoRequest;
import lindley.desarrolloxcliente.ws.bean.ActualizarCompromisoResponse;
import net.msonic.lib.JSONHelper;
import net.msonic.lib.ProxyBase;

public class ActualizarCompromisoProxy extends ProxyBase<ActualizarCompromisoResponse> {

	@InjectResource(R.string.urlwsDesarrolloxCliente)protected String urlWS;
	
	private List<CompromisoTO> compromisos;
	
	public List<UpdatePosicionTO> listaPosicionCompromiso;
	
	public List<UpdatePresentacionTO> listaPresentacionCompromiso;

	public List<CompromisoTO> getCompromisos() {
		return compromisos;
	}

	public void setCompromisos(List<CompromisoTO> compromisos) {
		this.compromisos = compromisos;
	}

	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/ActualizarCompromiso";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		ActualizarCompromisoRequest actualizarCompromisoRequest = new ActualizarCompromisoRequest();
		actualizarCompromisoRequest.setCompromisos(this.compromisos);
		actualizarCompromisoRequest.listaPosicionCompromiso = this.listaPosicionCompromiso;
		actualizarCompromisoRequest.listaPresentacionCompromiso = this.listaPresentacionCompromiso;
		String request = JSONHelper.serializar(actualizarCompromisoRequest);
		return request;
	}

	@Override
	protected ActualizarCompromisoResponse responseText(String json) {
		// TODO Auto-generated method stub
		ActualizarCompromisoResponse response = JSONHelper.desSerializar(json, ActualizarCompromisoResponse.class);
		return response;
	}
}
