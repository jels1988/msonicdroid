package pe.lindley.red.ws.bean;

import java.util.List;
import com.google.gson.annotations.SerializedName;
import pe.lindley.red.to.InventarioPrecioMarcadoTO;
import pe.lindley.util.ResponseBase;

public class ConsultarInventarioPrecioMarcadoResponse extends ResponseBase {

	@SerializedName("DAT")
	public List<InventarioPrecioMarcadoTO> data;

	public List<InventarioPrecioMarcadoTO> getData() {
		return data;
	}

	public void setData(List<InventarioPrecioMarcadoTO> data) {
		this.data = data;
	}
}
