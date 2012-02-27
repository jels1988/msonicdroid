package pe.lindley.mmil.ws.bean;

import java.util.List;
import pe.lindley.mmil.to.VendedorTO;
import pe.lindley.util.ResponseBase;
import com.google.gson.annotations.SerializedName;

public class ListarVendedorResponse  extends ResponseBase{
	
	@SerializedName("VEN")
	private List<VendedorTO> listaVendedor;

	public List<VendedorTO> getListaVendedor() {
		return listaVendedor;
	}

	public void setListaVendedor(List<VendedorTO> listaVendedor) {
		this.listaVendedor = listaVendedor;
	}
}
