package pe.lindley.sacc.ws.bean;

import java.util.List;

import pe.lindley.sacc.to.ContactoTO;
import pe.lindley.util.ResponseBase;

import com.google.gson.annotations.SerializedName;

public class ObtenerContactoResponse extends ResponseBase{
	
	@SerializedName("LCT")
	private List<ContactoTO> listaContacto;

	public List<ContactoTO> getListaContacto() {
		return listaContacto;
	}

	public void setListaContacto(List<ContactoTO> listaContacto) {
		this.listaContacto = listaContacto;
	}

}
