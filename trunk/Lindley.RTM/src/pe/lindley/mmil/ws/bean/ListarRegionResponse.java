package pe.lindley.mmil.ws.bean;

import java.util.List;

import pe.lindley.mmil.to.RegionTO;
import pe.lindley.util.ResponseBase;

import com.google.gson.annotations.SerializedName;

public class ListarRegionResponse  extends ResponseBase {
	
	@SerializedName("Reg")
	private List<RegionTO> regiones;

	public List<RegionTO> getRegiones() {
		return regiones;
	}

	public void setRegiones(List<RegionTO> regiones) {
		this.regiones = regiones;
	}
}
