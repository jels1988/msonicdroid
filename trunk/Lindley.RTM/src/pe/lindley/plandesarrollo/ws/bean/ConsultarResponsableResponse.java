package pe.lindley.plandesarrollo.ws.bean;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import pe.lindley.plandesarrollo.to.ResponsableTO;
import pe.lindley.util.ResponseBase;

public class ConsultarResponsableResponse extends ResponseBase {
	
	@SerializedName("RESP")
	private List<ResponsableTO> listaresponsables;

	public List<ResponsableTO> getListaresponsables() {
		return listaresponsables;
	}

	public void setListaresponsables(List<ResponsableTO> listaresponsables) {
		this.listaresponsables = listaresponsables;
	}

}