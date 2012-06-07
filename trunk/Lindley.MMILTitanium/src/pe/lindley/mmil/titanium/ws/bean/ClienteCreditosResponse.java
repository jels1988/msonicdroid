package pe.lindley.mmil.titanium.ws.bean;

import java.util.ArrayList;

import pe.lindley.mmil.titanium.to.ClienteCreditosTO;

import com.google.gson.annotations.SerializedName;

import net.msonic.lib.ResponseBase;

public class ClienteCreditosResponse extends ResponseBase {
	
	@SerializedName("CLI")
	public ArrayList<ClienteCreditosTO> clientesCreditos;
	
	
}
