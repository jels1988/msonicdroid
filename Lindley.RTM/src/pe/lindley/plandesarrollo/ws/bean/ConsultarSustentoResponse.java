package pe.lindley.plandesarrollo.ws.bean;

import java.util.List;
import com.google.gson.annotations.SerializedName;

import pe.lindley.plandesarrollo.to.SustentoTO;
import pe.lindley.util.ResponseBase;

public class ConsultarSustentoResponse extends ResponseBase {

	@SerializedName("SUST")
	private List<SustentoTO> listaSustento;

	public List<SustentoTO> getListaSustento() {
		return listaSustento;
	}

	public void setListaSustento(List<SustentoTO> listaSustento) {
		this.listaSustento = listaSustento;
	}
	
}
