package pe.lindley.mmil.titanium.ws.bean;

import pe.lindley.mmil.titanium.to.PizarraDataTO;


import com.google.gson.annotations.SerializedName;

import net.msonic.lib.ResponseBase;

public class MostrarPizarraResponse extends ResponseBase {
	@SerializedName("PIZ")
	public PizarraDataTO pizarra;
}
