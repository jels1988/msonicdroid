package pe.lindley.mmil.titanium.ws.bean;

import java.util.ArrayList;

import net.msonic.lib.ResponseBase;

import com.google.gson.annotations.SerializedName;

import pe.lindley.mmil.titanium.to.MixProductoTO;

public class MixProductoResponse extends ResponseBase{

	@SerializedName("MPR")
	public ArrayList<MixProductoTO> mixProductos;

}
