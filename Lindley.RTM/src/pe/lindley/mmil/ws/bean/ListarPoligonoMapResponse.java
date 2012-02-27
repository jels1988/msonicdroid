package pe.lindley.mmil.ws.bean;

import java.util.List;
import com.google.gson.annotations.SerializedName;
import pe.lindley.mmil.to.PoligonoMapTO;
import pe.lindley.util.ResponseBase;

public class ListarPoligonoMapResponse extends ResponseBase {

	@SerializedName("MAP")
	private List<PoligonoMapTO> listarPoligonoMap;

	public List<PoligonoMapTO> getListarPoligonoMap() {
		return listarPoligonoMap;
	}

	public void setListarPoligonoMap(List<PoligonoMapTO> listarPoligonoMap) {
		this.listarPoligonoMap = listarPoligonoMap;
	}

}
