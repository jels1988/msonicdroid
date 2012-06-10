package pe.lindley.mmil.titanium.ws.bean;

import java.util.ArrayList;

import pe.lindley.mmil.titanium.to.HistoryDetalleTO;


import com.google.gson.annotations.SerializedName;

import net.msonic.lib.ResponseBase;

public class ProfitHistoryResponse extends ResponseBase {
	
	@SerializedName("Detalle")
	public ArrayList<HistoryDetalleTO> detalle;

}
