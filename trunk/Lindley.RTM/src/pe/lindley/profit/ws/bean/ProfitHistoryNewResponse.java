package pe.lindley.profit.ws.bean;

import java.util.ArrayList;
import pe.lindley.profit.to.HistoryNewTO;
import pe.lindley.util.ResponseBase;

import com.google.gson.annotations.SerializedName;

public class ProfitHistoryNewResponse extends ResponseBase{

	@SerializedName("PFT")
	private ArrayList<HistoryNewTO> datos;

	public ArrayList<HistoryNewTO> getDatos() {
		return datos;
	}

	public void setDatos(ArrayList<HistoryNewTO> datos) {
		this.datos = datos;
	}

}