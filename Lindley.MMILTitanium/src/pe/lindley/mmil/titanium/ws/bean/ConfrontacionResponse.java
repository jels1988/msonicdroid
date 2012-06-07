package pe.lindley.mmil.titanium.ws.bean;

import java.util.ArrayList;

import pe.lindley.mmil.titanium.to.ConfrontacionTO;

import com.google.gson.annotations.SerializedName;

import net.msonic.lib.ResponseBase;

public class ConfrontacionResponse extends ResponseBase {

	
	@SerializedName("CON")
	public ArrayList<ConfrontacionTO> confrontacion;
	
}
