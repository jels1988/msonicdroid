package pe.lindley.red.ws.bean;

import java.util.List;
import com.google.gson.annotations.SerializedName;
import pe.lindley.red.to.OrdenTrabajoTO;
import pe.lindley.util.ResponseBase;

public class ConsultarOrdenTrabajoResponse extends ResponseBase{

	@SerializedName("DAT")
	private List<OrdenTrabajoTO> data;

	public List<OrdenTrabajoTO> getData() {
		return data;
	}

	public void setData(List<OrdenTrabajoTO> data) {
		this.data = data;
	}
	
}
