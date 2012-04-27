package pe.lindley.mmil.titanium.ws.bean;


import java.util.ArrayList;

import pe.lindley.mmil.titanium.to.VendedorTO;


import com.google.gson.annotations.SerializedName;

import net.msonic.lib.ResponseBase;

public class ListarVendedorResponse extends ResponseBase {
	
	@SerializedName("VEN")
	public ArrayList<VendedorTO> listaVendedor;
	
}
