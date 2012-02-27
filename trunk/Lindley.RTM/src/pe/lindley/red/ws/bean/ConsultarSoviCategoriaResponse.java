package pe.lindley.red.ws.bean;

import java.util.List;
import com.google.gson.annotations.SerializedName;
import pe.lindley.red.to.SoviCategoriaTO;
import pe.lindley.util.ResponseBase;

public class ConsultarSoviCategoriaResponse extends ResponseBase{

	@SerializedName("DAT")
	public List<SoviCategoriaTO> data;

	public List<SoviCategoriaTO> getData() {
		return data;
	}

	public void setData(List<SoviCategoriaTO> data) {
		this.data = data;
	}
	
}
