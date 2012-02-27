package pe.lindley.profit.ws.bean;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

import pe.lindley.profit.to.HistoryDetalleTO;
import pe.lindley.util.ResponseBase;

public class ProfitHistoryResponse extends ResponseBase {

	@SerializedName("Detalle")
	private ArrayList<HistoryDetalleTO> detalle;

	public ArrayList<HistoryDetalleTO> getDetalle() {
		return detalle;
	}

	public void setDetalle(ArrayList<HistoryDetalleTO> detalle) {
		this.detalle = detalle;
	}
	
}
