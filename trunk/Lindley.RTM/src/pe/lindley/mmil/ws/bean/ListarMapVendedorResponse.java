package pe.lindley.mmil.ws.bean;

import java.util.List;
import com.google.gson.annotations.SerializedName;
import pe.lindley.mmil.to.MapVendedorTO;
import pe.lindley.util.ResponseBase;

public class ListarMapVendedorResponse extends ResponseBase {
	
	@SerializedName("MVD")
	private List<MapVendedorTO> listaMapVendedor;

	public List<MapVendedorTO> getListaMapVendedor() {
		return listaMapVendedor;
	}

	public void setListaMapVendedor(List<MapVendedorTO> listaMapVendedor) {
		this.listaMapVendedor = listaMapVendedor;
	}

}
