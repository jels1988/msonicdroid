package pe.lindley.red.ws.bean;

import java.util.List;
import pe.lindley.red.to.InventarioPuntoContactoTO;
import pe.lindley.util.ResponseBase;

import com.google.gson.annotations.SerializedName;

public class ConsultarInventarioPuntoContactoResponse extends ResponseBase{

	@SerializedName("DAT")
	private List<InventarioPuntoContactoTO> data;

	public List<InventarioPuntoContactoTO> getData() {
		return data;
	}

	public void setData(List<InventarioPuntoContactoTO> data) {
		this.data = data;
	}
}
