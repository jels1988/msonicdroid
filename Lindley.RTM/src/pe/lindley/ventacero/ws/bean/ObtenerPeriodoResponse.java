package pe.lindley.ventacero.ws.bean;

import java.util.List;

import pe.lindley.util.ResponseBase;
import pe.lindley.ventacero.to.PeriodoTO;
import com.google.gson.annotations.SerializedName;

public class ObtenerPeriodoResponse extends ResponseBase{
	
	@SerializedName("PRI")
	private List<PeriodoTO> ListaPeriodo;

	public List<PeriodoTO> getListaPeriodo() {
		return ListaPeriodo;
	}

	public void setListaPeriodo(List<PeriodoTO> listaPeriodo) {
		ListaPeriodo = listaPeriodo;
	}
	
}
