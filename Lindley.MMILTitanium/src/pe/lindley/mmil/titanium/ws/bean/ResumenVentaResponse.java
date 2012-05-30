package pe.lindley.mmil.titanium.ws.bean;

import java.util.ArrayList;

import pe.lindley.mmil.titanium.to.ResumenVentaTO;

import com.google.gson.annotations.SerializedName;

import net.msonic.lib.ResponseBase;

public class ResumenVentaResponse extends ResponseBase {

	
	@SerializedName("RVN")
	public ArrayList<ResumenVentaTO> resumenVenta;

}
