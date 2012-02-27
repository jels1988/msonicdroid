package pe.lindley.ficha.ws.bean;

import java.util.List;

import com.google.gson.annotations.SerializedName;
import pe.lindley.ficha.to.ContactoTO;
import pe.lindley.util.ResponseBase;

public class ObtenerContactoResponse extends ResponseBase{

	@SerializedName("CTO")
	private List<ContactoTO> contactos;

	public List<ContactoTO> getContactos() {
		return contactos;
	}

	public void setContactos(List<ContactoTO> contactos) {
		this.contactos = contactos;
	}
}