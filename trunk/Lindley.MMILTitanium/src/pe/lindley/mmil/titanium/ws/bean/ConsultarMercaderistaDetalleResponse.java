package pe.lindley.mmil.titanium.ws.bean;

import java.util.ArrayList;

import pe.lindley.mmil.titanium.to.MercaderistaDetalleTO;

import com.google.gson.annotations.SerializedName;

import net.msonic.lib.ResponseBase;

public class ConsultarMercaderistaDetalleResponse extends ResponseBase {
	
	
	@SerializedName("MDT")
	public ArrayList<MercaderistaDetalleTO> detalle;
}
