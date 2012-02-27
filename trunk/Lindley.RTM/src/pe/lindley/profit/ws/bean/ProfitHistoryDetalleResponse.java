package pe.lindley.profit.ws.bean;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

import pe.lindley.profit.to.HistoryValueTO;
import pe.lindley.util.ResponseBase;

public class ProfitHistoryDetalleResponse extends ResponseBase {

	@SerializedName("Detalle")
	private ArrayList<HistoryValueTO> detalle;

	public ArrayList<HistoryValueTO> getDetalle() {
		return detalle;
	}

	public void setDetalle(ArrayList<HistoryValueTO> detalle) {
		this.detalle = detalle;
	}
	
}
