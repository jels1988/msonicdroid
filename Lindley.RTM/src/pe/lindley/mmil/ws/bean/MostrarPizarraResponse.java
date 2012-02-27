package pe.lindley.mmil.ws.bean;

import java.util.List; 
import com.google.gson.annotations.SerializedName;
import pe.lindley.mmil.to.PizarraDataTO;
import pe.lindley.util.ResponseBase;

public class MostrarPizarraResponse extends ResponseBase {

	@SerializedName("PIZ")
	private List<PizarraDataTO> listaPizarra;

	public List<PizarraDataTO> getListaPizarra() {
		return listaPizarra;
	}

	public void setListaPizarra(List<PizarraDataTO> listaPizarra) {
		this.listaPizarra = listaPizarra;
	}
	
}
