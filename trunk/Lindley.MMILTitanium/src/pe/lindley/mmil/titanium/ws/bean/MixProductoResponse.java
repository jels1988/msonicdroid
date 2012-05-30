package pe.lindley.mmil.titanium.ws.bean;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

import pe.lindley.mmil.titanium.to.MixProductoTO;

public class MixProductoResponse {

	@SerializedName("MPR")
	public ArrayList<MixProductoTO> mixProductos;

}
