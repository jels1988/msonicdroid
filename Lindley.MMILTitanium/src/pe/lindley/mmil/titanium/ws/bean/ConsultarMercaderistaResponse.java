package pe.lindley.mmil.titanium.ws.bean;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

import pe.lindley.mmil.titanium.to.MercaderistaTO;

import net.msonic.lib.ResponseBase;

public class ConsultarMercaderistaResponse extends ResponseBase {
	
	@SerializedName("MER")
	public ArrayList<MercaderistaTO> mercaderistas;
}
