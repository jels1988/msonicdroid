package lindley.desarrolloxcliente.ws.bean;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lindley.desarrolloxcliente.to.InformacionAdicionalTO;
import lindley.desarrolloxcliente.to.OportunidadTO;
import lindley.desarrolloxcliente.to.PosicionCompromisoTO;
import lindley.desarrolloxcliente.to.PresentacionCompromisoTO;

public class GuardarDesarrolloRequest {
	
	@Expose()
	@SerializedName("CSIS")
	private List<OportunidadTO> oportunidadSistema;
	
	@Expose()
	@SerializedName("CDES")
	private List<OportunidadTO> oportunidadDesarrollador;

	@Expose()
	@SerializedName("INF")
	private InformacionAdicionalTO informacion;
		
	@Expose()
	@SerializedName("POS")
	public List<PosicionCompromisoTO> listPosicion;

	@Expose()
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
