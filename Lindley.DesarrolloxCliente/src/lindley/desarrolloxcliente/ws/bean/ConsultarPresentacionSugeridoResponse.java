package lindley.desarrolloxcliente.ws.bean;

import java.util.List;

import lindley.desarrolloxcliente.to.PresentacionTO;

import com.google.gson.annotations.SerializedName;

import net.msonic.lib.ResponseBase;

public class ConsultarPresentacionSugeridoResponse extends ResponseBase {

	@SerializedName("PRE")
	private List<PresentacionTO> listaPresentacion;

	public List<PresentacionTO> getListaPresentacion() {
		return listaPresentacion;
	}

	public void setListaPresentacion(List<PresentacionTO> listaPresentacion) {
		this.listaPresentacion = listaPresentacion;
	}
}
