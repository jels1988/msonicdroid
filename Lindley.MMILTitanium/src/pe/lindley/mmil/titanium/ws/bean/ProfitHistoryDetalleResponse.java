package pe.lindley.mmil.titanium.ws.bean;

import java.util.ArrayList;

import pe.lindley.mmil.titanium.to.HistoryValueTO;



import com.google.gson.annotations.SerializedName;

import net.msonic.lib.ResponseBase;

public class ProfitHistoryDetalleResponse extends ResponseBase {

	
	@SerializedName("Detalle")
	public ArrayList<HistoryValueTO> detalle;

}
