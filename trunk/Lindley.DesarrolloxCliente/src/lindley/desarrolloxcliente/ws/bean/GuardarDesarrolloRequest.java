package lindley.desarrolloxcliente.ws.bean;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import lindley.desarrolloxcliente.to.InformacionAdicionalTO;
import lindley.desarrolloxcliente.to.OportunidadTO;
import lindley.desarrolloxcliente.to.PosicionCompromisoTO;
import lindley.desarrolloxcliente.to.PresentacionCompromisoTO;

public class GuardarDesarrolloRequest {
	
	@SerializedName("CSIS")
	private List<OportunidadTO> oportunidadSistema;
	
	@SerializedName("CDES")
	private List<OportunidadTO> oportunidadDesarrollador;

	@SerializedName("INF")
	private InformacionAdicionalTO informacion;
		
	@SerializedName("POS")
	public List<PosicionCompromisoTO> listPosicion;

	@SerializedName("PRE")
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
}
