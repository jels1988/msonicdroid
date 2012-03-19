package lindley.desarrolloxcliente.ws.service;

import java.util.List;

import roboguice.inject.InjectResource;

import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.to.InformacionAdicionalTO;
import lindley.desarrolloxcliente.to.OportunidadTO;
import lindley.desarrolloxcliente.to.PosicionCompromisoTO;
import lindley.desarrolloxcliente.to.PresentacionCompromisoTO;
import lindley.desarrolloxcliente.ws.bean.GuardarDesarrolloRequest;
import lindley.desarrolloxcliente.ws.bean.GuardarDesarrolloResponse;
import net.msonic.lib.JSONHelper;
import net.msonic.lib.ProxyBase;

public class GuardarDesarrolloProxy extends ProxyBase<GuardarDesarrolloResponse>{
	
	@InjectResource(R.string.urlwsDesarrolloxCliente)protected String urlWS;
		
	private List<OportunidadTO> oportunidadSistema;
		
	private List<OportunidadTO> oportunidadDesarrollador;
	
	private InformacionAdicionalTO informacion;

	public List<PosicionCompromisoTO> listPosicion;

	public List<PresentacionCompromisoTO> listPresentacion;
	
	public List<OportunidadTO> getOportunidadSistema() {
		return oportunidadSistema;
	}

	public void setOportunidadSistema(List<OportunidadTO> oportunidadSistema) {
		this.oportunidadSistema = oportunidadSistema;
	}

	public List<OportunidadTO> getOportunidadDesarrollador() {
		return oportunidadDesarrollador;
	}

	public void setOportunidadDesarrollador(
			List<OportunidadTO> oportunidadDesarrollador) {
		this.oportunidadDesarrollador = oportunidadDesarrollador;
	}

	public InformacionAdicionalTO getInformacion() {
		return informacion;
	}

	public void setInformacion(InformacionAdicionalTO informacion) {
		this.informacion = informacion;
	}

	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/GuardarCompromiso";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		GuardarDesarrolloRequest guardarDesarrolloRequest = new GuardarDesarrolloRequest();
		guardarDesarrolloRequest.listPosicion = this.listPosicion;
		guardarDesarrolloRequest.listPresentacion = this.listPresentacion;
		guardarDesarrolloRequest.setInformacion(this.informacion);
		guardarDesarrolloRequest.setOportunidadDesarrollador(this.oportunidadDesarrollador);
		guardarDesarrolloRequest.setOportunidadSistema(this.oportunidadSistema);
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
