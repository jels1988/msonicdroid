package pe.lindley.red.ws.bean;

import java.util.List;

import pe.lindley.red.to.InventarioPrecioSugeridoTO;
import pe.lindley.util.ResponseBase;

import com.google.gson.annotations.SerializedName;

public class ConsultarInventarioPrecioSugeridoResponse extends ResponseBase{

	@SerializedName("DAT")
	public List<InventarioPrecioSugeridoTO> data;

	public List<InventarioPrecioSugeridoTO> getData() {
		return data;
	}

	public void setData(List<InventarioPrecioSugeridoTO> data) {
		this.data = data;
	}
	 
}
